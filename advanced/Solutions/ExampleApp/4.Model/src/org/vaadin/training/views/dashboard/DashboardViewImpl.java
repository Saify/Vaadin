package org.vaadin.training.views.dashboard;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DashboardViewImpl extends VerticalLayout implements View,
		DashboardView {

	private Label placeholder;

	public DashboardViewImpl() {
		setSpacing(true);
		setMargin(true);

		placeholder = new Label("This is a placeholder label");
		addComponent(placeholder);
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}

}
