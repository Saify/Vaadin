package com.vaadin.training.fundamentals.exercises.ex4;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BindingProperties extends VerticalLayout implements View {

	public BindingProperties() {
		setSpacing(true);

		// TODO: Create a slider and add it to the layout

		// TODO: Create a label that show's the slider's value

		// TODO: Create a ProgressBar that visualizes the chosen value in the
		// slider

		setMargin(true);
		setSpacing(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
