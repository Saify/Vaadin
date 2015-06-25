package org.vaadin.training.views.department;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;

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
		ValueChangeListener, DepartmentView, EmployeeEditorHandler,
		PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private Table table;
	private EmployeeEditor employeeEditor = new EmployeeEditor(this);
	private DepartmentInfo departmentInfo;
	private BeanItemContainer<EmployeeProxy> personContainer;
	private DepartmentPresenter presenter;
	private DepartmentModel model;

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
		createContainer(new ArrayList<EmployeeProxy>());
		table = new Table();
		table.setContainerDataSource(personContainer);
		table.setSizeFull();
		table.setSelectable(true);
		table.setImmediate(true);
		table.setVisibleColumns(new String[] { "firstName", "lastName", "email" });
		table.addValueChangeListener(this);
	}

	private void createContainer(Collection<EmployeeProxy> employees) {
		personContainer = new BeanItemContainer<EmployeeProxy>(
				EmployeeProxy.class);
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

	private void updateDepartmentHeader() {
		departmentInfo.setDepartment(model.getDepartmentName());
	}

	private void updateEmployeeTable() {
		personContainer.removeAllItems();
		personContainer.addAll(model.getEmployees());
	}

	/*
	 * (non-Javadoc) Example implementation for table value change listener
	 */
	@Override
	public void valueChange(ValueChangeEvent event) {
		getPresenter().employeeSelected((EmployeeProxy) table.getValue());
	}

	@Override
	public void showEmployeeInForm(EmployeeProxy employee) {
		if (table.getValue() == null) {
			employeeEditor.setEnabled(false);
			return;
		}

		employeeEditor.setEnabled(true);
		employeeEditor.setEmployee(personContainer.getItem(table.getValue()));
	}

	@Override
	public EmployeeProxy commitChanges() {
		return employeeEditor.commit();
	}

	@Override
	public void selectEmployee(final EmployeeProxy employee) {
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

	private void select(EmployeeProxy employee) {
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
	public void setModel(DepartmentModel model) {
		this.model = model;
		updateDepartmentHeader();
		updateEmployeeTable();
		model.addPropertyChangeListener(this);
	}

	public void discardChanges() {
		employeeEditor.discard();
	}

	private void updateProgressState() {
		departmentInfo.setLoadingState(model.getDataLoadingState());
	}

	@Override
	public void propertyChange(final PropertyChangeEvent event) {
		UI ui = getUI();
		if (!isAttached() || ui.getSession().hasLock()) {
			updateModelData(event);
		} else {
			ui.access(new Runnable() {

				@Override
				public void run() {
					updateModelData(event);
				}
			});
		}

	}

	private void updateModelData(final PropertyChangeEvent event) {
		if (DepartmentModel.PROPERTY_DEPARMENT_NAME.equals(event
				.getPropertyName())) {
			updateDepartmentHeader();
		} else if (DepartmentModel.PROPERTY_EMPLOYEES.equals(event
				.getPropertyName())) {
			updateEmployeeTable();
		} else if (DepartmentModel.PROPERTY_DATA_LOADING_STATE.equals(event
				.getPropertyName())) {
			updateProgressState();
		}
	}

	@Override
	public void scrollToSelection() {
		if (table.getValue() != null) {
			table.setCurrentPageFirstItemId(table.getValue());
		}
	}

}
