package org.vaadin.training.views.dashboard;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class DashboardView extends VerticalLayout implements View {

	public DashboardView() {
		addComponent(new Label("This is a placeholder view"));
	}

	@Override
	public void enter(ViewChangeEvent event) {
	}


}
