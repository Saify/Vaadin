package com.vaadin.training.fundamentals.exercises.ex9;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;

@SuppressWarnings("serial")
public class DataBinding extends GridLayout implements View {

	public DataBinding() {
		super(2, 2);
		setSpacing(true);
		// TODO Add two Labels, one ComboBox and one TextField. Use Vaadin's
		// data model to bind the components to each other. The values to a
		// ComboBox are populated in the same way as the rows to a Table, hint
		// hint.

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
