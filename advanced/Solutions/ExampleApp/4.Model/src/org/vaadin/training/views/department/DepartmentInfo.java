package org.vaadin.training.views.department;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;

public class DepartmentInfo extends HorizontalLayout {

	private Label departmentName;

	private ProgressBar loading;

	public DepartmentInfo() {
		setMargin(true);

		departmentName = new Label();
		departmentName.setValue("Department: ");
		addComponent(departmentName);

		setWidth("100%");
		loading = new ProgressBar();
		loading.setCaption("Loading data, please hold...");
		loading.setVisible(false);
		loading.setWidth("200px");
		addComponent(loading);
		
		setComponentAlignment(departmentName, Alignment.MIDDLE_LEFT);
		setComponentAlignment(loading, Alignment.MIDDLE_RIGHT);
	}

	public void setDepartment(String name) {
		departmentName.setValue("Department: " + name);
	}
	
	public void setLoadingState(float state) {
		if(state == 1) {
			loading.setVisible(false);
		} else {
			loading.setValue(state);
			loading.setVisible(true);
		}
	}

}
