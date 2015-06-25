package org.vaadin.training;

import org.vaadin.training.views.auditing.AuditingViewImpl;
import org.vaadin.training.views.dashboard.DashboardView;
import org.vaadin.training.views.department.DepartmentViewImpl;

import com.vaadin.annotations.Push;
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

@Push
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

		createNavigator(panel);
		registerViews();
		navigateToDefaultView();

		rootLayout.addComponent(panel);
		rootLayout.setExpandRatio(panel, 1);

		setContent(rootLayout);

	}

	private void navigateToDefaultView() {
		if ("".equals(navigator.getState())) {
			navigator.navigateTo("dashboard");
		}
	}

	private void registerViews() {
		navigator.addView("dashboard", DashboardView.class);
		navigator.addView("department", DepartmentViewImpl.class);
		navigator.addView("auditing", new AuditingViewImpl());
	}

	private void createNavigator(Panel panel) {
		navigator = new Navigator(this, panel);
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
			navigator.navigateTo("dashboard");
		} else if (department.equals(event.getButton())) {
			navigator.navigateTo("department");
		} else if (auditing.equals(event.getButton())) {
			navigator.navigateTo("auditing");
		}
	}

}
