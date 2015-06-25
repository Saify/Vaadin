package org.vaadin.training.views.department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.vaadin.training.authentication.Department;
import org.vaadin.training.data.Person;

import com.vaadin.cdi.CDIView;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

@CDIView("department")
public class DepartmentViewImpl extends VerticalSplitPanel implements View,
		ValueChangeListener, DepartmentView, EmployeeEditorHandler {

	private static final long serialVersionUID = 1L;
	private Table table;

	private EmployeeEditor employeeEditor = new EmployeeEditor(this);
	private DepartmentInfo departmentInfo;
	private BeanItemContainer<Person> personContainer;
	
	@Inject
	private DepartmentPresenter presenter;

	public DepartmentViewImpl() {
		setSizeFull();

		VerticalLayout departmentInfoAndEmployeeTableLayout = new VerticalLayout();
		departmentInfoAndEmployeeTableLayout.setSizeFull();
		departmentInfoAndEmployeeTableLayout.setSpacing(true);

		departmentInfo = new DepartmentInfo();
		departmentInfoAndEmployeeTableLayout.addComponent(departmentInfo);

		createEmployeeTable();
		departmentInfoAndEmployeeTableLayout.addComponent(table);
		departmentInfoAndEmployeeTableLayout.setExpandRatio(table, 1f);
		addComponent(departmentInfoAndEmployeeTableLayout);
		addComponent(employeeEditor);
	}
	
	@PostConstruct
	private void init() {
		presenter.setView(this);
	}

	private void createEmployeeTable() {
		createContainer(new ArrayList<Person>());
		table = new Table();
		table.setContainerDataSource(personContainer);
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setVisibleColumns(new Object[] { "firstName", "lastName", "email" });
		table.addValueChangeListener(this);
	}

	private void createContainer(Collection<Person> employees) {
		personContainer = new BeanItemContainer<Person>(Person.class);
		personContainer.addAll(employees);
	}

	@Override
	public void onSaveClick() {
		getPresenter().saveClicked();
	}

	@Override
	public void onCancelClick() {
		getPresenter().cancelClicked();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		getPresenter().onViewEnter(event.getParameters());
	}

	private void updateDepartmentHeader(Department department) {
		departmentInfo.setDepartment(department);
	}

	private void updateEmployeeTable(List<Person> employees) {
		personContainer.removeAllItems();
		personContainer.addAll(employees);
	}

	/*
	 * (non-Javadoc) Example implementation for table value change listener
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {
		getPresenter().employeeSelected((Person) table.getValue());
	}

	@Override
	public void setEmployees(List<Person> employees) {
		updateEmployeeTable(employees);
	}

	@Override
	public void showEmployeeInForm(Person employee) {
		if (employee == null) {
			employeeEditor.setEnabled(false);
			return;
		}
		
		employeeEditor.setEnabled(true);
		employeeEditor.setPerson(personContainer.getItem(employee));
	}

	@Override
	public Person commitChanges() {
		return employeeEditor.commit();
	}

	@Override
	public void selectEmployee(Person employee) {
		table.select(employee);
		if(employee != null) {
			Page.getCurrent().setUriFragment("!department/" + employee.getId(), false);
		} else {
			Page.getCurrent().setUriFragment("!department", false);
		}
	}

	private DepartmentPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void discardChanges() {
		employeeEditor.discard();
	}

	@Override
	public void setDepartment(Department department) {
		updateDepartmentHeader(department);
	}

	@Override
	public void scrollToSelection() {
		if(table.getValue() != null) {
			table.setCurrentPageFirstItemId(table.getValue());
		}
		
	}

}
