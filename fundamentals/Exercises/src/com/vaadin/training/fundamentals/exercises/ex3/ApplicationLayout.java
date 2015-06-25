package com.vaadin.training.fundamentals.exercises.ex3;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ApplicationLayout extends VerticalLayout implements View {

	public ApplicationLayout() {
		Label header = new Label("This is the header. My height is 150 pixels");
		header.setStyleName("header");
		header.setSizeFull();
		HorizontalLayout labelLayout = new HorizontalLayout(header);
		labelLayout.setSizeFull();
		labelLayout.setHeight("150px");
		Label navigation = new Label(
				"This is the navigation area. My width is 25% of the window.");
		navigation.setStyleName("navigation");

		Label content = new Label("This is the content area");
		content.setStyleName("content");
		HorizontalLayout center = new HorizontalLayout(navigation, content);
		navigation.setSizeFull();
		content.setSizeFull();
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
		setExpandRatio(labelLayout, 1);
		setExpandRatio(center, 2);
		setExpandRatio(footer, 1);
		setSizeFull();
		setMargin(false);
	}

	@Override
	public void enter(ViewChangeEvent event) {

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
