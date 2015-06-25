package org.vaadin.training;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class ExampleApp extends UI implements ClickListener {
	private static final long serialVersionUID = 1L;
	private Navigator navigator;
	private Button dashboard;
	private Button department;
	private Button auditing;

	@Override
	public void init(VaadinRequest request) {

		Label label = new Label("Hello Vaadin user");
		setContent(label);

		HorizontalLayout rootLayout = new HorizontalLayout();
		rootLayout.setSizeFull();

		createNavigationBar(rootLayout);

		Panel panel = new Panel();
		panel.setSizeFull();

		navigator = createNavigator(panel);
		registerViews();
		navigateToDefaultView();

		rootLayout.addComponent(panel);
		rootLayout.setExpandRatio(panel, 1);

		setContent(rootLayout);

	}
	
	private void navigateToDefaultView() {
		// If no URI fragment is defined, then navigate to default view
		if (navigator!=null && "".equals(navigator.getState())) {
			// TODO: switch the view to the "dashboard" view
		}
	}

	private void registerViews() {
		// TODO register all views
	}

	private Navigator createNavigator(Panel panel) {
		// TODO create navigator instance
		return null;
	}

	private void createNavigationBar(HorizontalLayout rootLayout) {
		VerticalLayout sideNavigation = new VerticalLayout();
		sideNavigation.setWidth("150px");
		sideNavigation.setMargin(true);
		sideNavigation.setSpacing(true);

		dashboard = new Button("Dashboard", this);
		department = new Button("Department", this);
		auditing = new Button("Auditing", this);
		sideNavigation.addComponent(dashboard);
		sideNavigation.addComponent(department);
		sideNavigation.addComponent(auditing);

		rootLayout.addComponent(sideNavigation);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (dashboard.equals(event.getButton())) {
			// TODO switch view to dashboard
		} else if (department.equals(event.getButton())) {
			// TODO switch view to department
		} else if (auditing.equals(event.getButton())) {
			// TODO switch view to auditing
		}
	}

}
