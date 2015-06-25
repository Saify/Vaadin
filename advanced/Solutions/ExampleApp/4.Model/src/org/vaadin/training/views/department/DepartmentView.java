package org.vaadin.training.views.department;

public interface DepartmentView {

	public void setModel(DepartmentModel model);

	public void showEmployeeInForm(EmployeeProxy employee);

	public EmployeeProxy commitChanges();

	public void discardChanges();

	public void selectEmployee(EmployeeProxy employee);

	public void scrollToSelection();
	
}
