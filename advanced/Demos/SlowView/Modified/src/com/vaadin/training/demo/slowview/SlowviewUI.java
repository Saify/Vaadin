package com.vaadin.training.demo.slowview;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class SlowviewUI extends UI {

	private Button loginBtn;
	private VerticalLayout layout;

	@Override
	public void init(VaadinRequest request) {
		layout = new VerticalLayout();
		layout.setSizeFull();

		loginBtn = new Button("Login", new ClickListener() {

			public void buttonClick(ClickEvent event) {
				buildTabSheet();
			}
		});

		layout.addComponent(loginBtn);
		layout.setComponentAlignment(loginBtn, Alignment.MIDDLE_CENTER);

		setContent(layout);
	}

	private void buildTabSheet() {
		TabSheet tabSheet = new TabSheet();
		tabSheet.setHeight("80%");
		tabSheet.setWidth("80%");

		SlowView view1 = new SlowView("View 1");
		view1.buildView();
		tabSheet.addComponent(view1);
		tabSheet.addComponent(new SlowView("View 2"));
		tabSheet.addComponent(new SlowView("View 3"));
		tabSheet.addComponent(new SlowView("View 4"));
		tabSheet.addComponent(new SlowView("View 5"));
		tabSheet.addComponent(new SlowView("View 6"));
		tabSheet.addComponent(new SlowView("View 7"));

		tabSheet.addSelectedTabChangeListener(new SelectedTabChangeListener() {

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				((SlowView) event.getTabSheet().getSelectedTab()).buildView();

			}
		});

		layout.replaceComponent(loginBtn, tabSheet);
		layout.setComponentAlignment(tabSheet, Alignment.MIDDLE_CENTER);

	}

}
