package org.vaadin.training.views.department;

import java.util.ArrayList;
import java.util.Collection;

import org.vaadin.training.authentication.AuthenticationService;
import org.vaadin.training.backend.PersonService;
import org.vaadin.training.data.Person;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class DepartmentView extends VerticalSplitPanel implements
		ValueChangeListener, EmployeeEditorHandler {

	private static final long serialVersionUID = 1L;
	private PersonService service = new PersonService();
	private Table table;

	private EmployeeEditor employeeEditor = new EmployeeEditor(this);
	private DepartmentInfo departmentInfo;
	private BeanItemContainer<Person> personContainer;

	public DepartmentView() {
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

	private void updateDepartmentHeader() {
		departmentInfo.setDepartment(AuthenticationService.getLoggedInUser()
				.getDepartment());
	}

	private void updateEmployeeTable() {
		personContainer.removeAllItems();
		personContainer.addAll(service.getEmployees());
	}

	/*
	 * (non-Javadoc) Example implementation for table value change listener
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {
		if (table.getValue() == null) {
			employeeEditor.setEnabled(false);
			return;
		}

		employeeEditor.setEnabled(true);
		employeeEditor.setPerson(personContainer.getItem(table.getValue()));
	}

	@Override
	public void onSaveClick() {
		Person person = employeeEditor.commit();
		service.save(person);
		table.setValue(null);
	}

	@Override
	public void onCancelClick() {
		employeeEditor.discard();
	}

}
