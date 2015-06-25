package com.vaadin.training.fundamentals.exercises.ex5;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;

public class DataValidation extends CustomComponent implements View {

	private static final long serialVersionUID = 1L;

	public DataValidation() {
		FormLayout mainLayout = new FormLayout();
		mainLayout.setSizeUndefined();
		setCompositionRoot(mainLayout);
		
		// TODO Create a TextField
		// TODO Create a validator instance
		// TODO Bind the validator to the TextField
		// TODO Add the TextField to mainLayout

		// TODO Repeat the steps for each validator type
		
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
