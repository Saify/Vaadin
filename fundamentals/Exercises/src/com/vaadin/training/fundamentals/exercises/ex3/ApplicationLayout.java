package com.vaadin.training.fundamentals.exercises.ex3;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ApplicationLayout extends VerticalLayout implements View {

	HorizontalLayout center;

	public ApplicationLayout() {
		Label header = new Label("This is the header. My height is 150 pixels");
		header.setStyleName("header");
		header.setSizeFull();
		HorizontalLayout labelLayout = new HorizontalLayout(header);
		labelLayout.setSizeFull();
		labelLayout.setHeight("150px");

		// Navigation area
		Label navigation = new Label(
				"This is the navigation area. My width is 25% of the window.");
		navigation.setStyleName("navigation");
		navigation.setSizeFull();

		// Content area
		Label content = new Label("This is the content area");
		content.setStyleName("content");
		content.setSizeFull();

		// Center area
		center = new HorizontalLayout(navigation, content);
		center.setExpandRatio(navigation, 1);
		center.setExpandRatio(content, 3);
		center.setSizeFull();

		Label footer = new Label(
				"This is the footer area. My height is 100 pixels");
		footer.setStyleName("footer");
		footer.setHeight("100px");
		addComponent(labelLayout);
		addComponent(center);
		addComponent(footer);
		setExpandRatio(center, 1);
		setSizeFull();
		setMargin(false);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		System.out.println("ENTER");
		if ("ScrollView".equals(event.getParameters())) {
			center.removeAllComponents();
			createScrollView();
		}
	}

	private void createScrollView() {
		Panel panel = new Panel();
		final CssLayout panelContent = new CssLayout();
		panel.setContent(panelContent);
		panel.setSizeFull();

		Button addButton = new Button("Add Buttons", new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				Button button = createButton();
				panelContent.addComponent(button);

			}
		});
		center.addComponent(addButton);
		center.setComponentAlignment(addButton, Alignment.MIDDLE_CENTER);
		center.addComponent(panel);
		center.setExpandRatio(addButton, 1);
		center.setExpandRatio(panel, 3);
	}

	/**
	 * Ignore this method for now.
	 * 
	 * @return
	 */
	private Button createButton() {
		NativeButton button = new NativeButton("Ignore");
		button.setHeight("200px");
		button.setWidth("200px");
		return button;
	}

}
