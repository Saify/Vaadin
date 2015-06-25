package com.vaadin.training.fundamentals.exercises;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@Theme("exercisestheme")
@SuppressWarnings("serial")
public class ExercisesUI extends UI {

	private Navigator navigator;
	private VerticalLayout layout;

	@Override
	protected void init(VaadinRequest request) {
		layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSizeFull();
		setContent(layout);

		navigator = new Navigator(this, layout);
		navigator.addView("", new MainView(navigator));
	}

}
