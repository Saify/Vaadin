package org.vaadin.training.views.department;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class DepartmentModel {

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	
	public final static String PROPERTY_EMPLOYEES = "employees";
	public final static String PROPERTY_DATA_LOADING_STATE = "dataLoadingState";
	public final static String PROPERTY_DEPARMENT_NAME = "departmentName";
	
	
	private List<EmployeeProxy> employees = new ArrayList<EmployeeProxy>();
	private String departmentName;
	private float dataLoadingState;
	
	public void addPropertyChangeListener(PropertyChangeListener arg0) {
		propertyChangeSupport.addPropertyChangeListener(arg0);
	}

	public void addPropertyChangeListener(String arg0,
			PropertyChangeListener arg1) {
		propertyChangeSupport.addPropertyChangeListener(arg0, arg1);
	}

	public void removePropertyChangeListener(PropertyChangeListener arg0) {
		propertyChangeSupport.removePropertyChangeListener(arg0);
	}

	public void removePropertyChangeListener(String arg0,
			PropertyChangeListener arg1) {
		propertyChangeSupport.removePropertyChangeListener(arg0, arg1);
	}

	public List<EmployeeProxy> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeProxy> employees) {
		List<EmployeeProxy> old = this.employees;
		this.employees = employees;
		propertyChangeSupport.firePropertyChange(PROPERTY_EMPLOYEES, old, employees);
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		String old = this.departmentName;
		this.departmentName = departmentName;
		propertyChangeSupport.firePropertyChange(PROPERTY_DEPARMENT_NAME, old, departmentName);
	}

	public float getDataLoadingState() {
		return dataLoadingState;
	}

	public void setDataLoadingState(float dataLoadingState) {
		float old = this.dataLoadingState;
		this.dataLoadingState = dataLoadingState;
		propertyChangeSupport.firePropertyChange(PROPERTY_DATA_LOADING_STATE, old, dataLoadingState);
	}


}
