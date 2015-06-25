package org.vaadin.training.views.department;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.vaadin.training.authentication.Department;
import org.vaadin.training.data.Person;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class DepartmentViewImpl extends VerticalSplitPanel implements View,
		ValueChangeListener, DepartmentView, EmployeeEditorHandler {

	private static final long serialVersionUID = 1L;
	private Table table;

	private EmployeeEditor employeeEditor = new EmployeeEditor(this);
	private DepartmentInfo departmentInfo;
	private BeanItemContainer<Person> personContainer;
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

		presenter = new DepartmentPresenter(this);
	}

	private void createEmployeeTable() {
		createContainer(new ArrayList<Person>());
		table = new Table();
		table.setContainerDataSource(personContainer);
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setVisibleColumns(new String[] { "firstName", "lastName", "email" });
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
	public void setEmployees(final List<Person> employees) {
		UI ui = getUI();
		if (!isAttached() || ui.getSession().hasLock()) {
			updateEmployeeTable(employees);
		} else {
			ui.access(new Runnable() {

				@Override
				public void run() {
					updateEmployeeTable(employees);
				}
			});
		}
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
	public void selectEmployee(final Person employee) {
		UI ui = getUI();
		if (!isAttached() || ui.getSession().hasLock()) {
			select(employee);

		} else {
			ui.access(new Runnable() {

				@Override
				public void run() {
					select(employee);
				}
			});
		}
	}

	private void select(Person employee) {
		table.select(employee);
		if (employee != null) {
			Page.getCurrent().setUriFragment("!department/" + employee.getId(),
					false);
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
	public void setDataLoadingState(final float state) {
		UI ui = getUI();
		if (!isAttached() || ui.getSession().hasLock()) {
			departmentInfo.setLoadingState(state);
		} else {
			ui.access(new Runnable() {

				@Override
				public void run() {
					departmentInfo.setLoadingState(state);
				}
			});
		}
	}

	@Override
	public void scrollToSelection() {
		if (table.getValue() != null) {
			table.setCurrentPageFirstItemId(table.getValue());
		}
	}

}
