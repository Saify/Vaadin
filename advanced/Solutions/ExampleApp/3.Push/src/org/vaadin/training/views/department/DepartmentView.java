package org.vaadin.training.views.department;

import java.util.List;

import org.vaadin.training.authentication.Department;
import org.vaadin.training.data.Person;

public interface DepartmentView {

	public void setEmployees(List<Person> employees);

	public void showEmployeeInForm(Person employee);

	public Person commitChanges();

	public void discardChanges();

	public void selectEmployee(Person employee);

	public void setDepartment(Department department);

	public void setDataLoadingState(float percentageProgress);

	public void scrollToSelection();

}
