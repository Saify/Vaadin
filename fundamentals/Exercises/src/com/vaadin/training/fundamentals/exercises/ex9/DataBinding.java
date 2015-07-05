package com.vaadin.training.fundamentals.exercises.ex9;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class DataBinding extends GridLayout implements View {

	public DataBinding() {
		super(2, 2);
		setSpacing(true);
		// TODO Add two Labels, one ComboBox and one TextField. Use Vaadin's
		// data model to bind the components to each other. The values to a
		// ComboBox are populated in the same way as the rows to a Table, hint
		// hint.
		TextField textField = new TextField();
		textField.setImmediate(true);
		addComponent(textField);

		Label label1 = new Label();
		addComponent(label1);
		label1.setPropertyDataSource(textField);

		ComboBox comboBox = new ComboBox();

		comboBox.addItem("Faisalabad");
		comboBox.addItem("Lahore");
		comboBox.addItem("Isalamabad");
		comboBox.setItemCaption("Faisalabad", "Faisalabad");
		comboBox.setItemCaption("Lahore", "Lahore");
		comboBox.setItemCaption("Isalamabad", "Isalamabad");
		comboBox.setImmediate(true);
		comboBox.setCaption("Select City");
		addComponent(comboBox);

		Label label2 = new Label();
		label2.setPropertyDataSource(comboBox);
		addComponent(label2);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
