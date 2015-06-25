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

		TextField textField = new TextField();
		textField.setImmediate(true);
		addComponent(textField);

		Label label1 = new Label();
		label1.setPropertyDataSource(textField);
		addComponent(label1);

		ComboBox comboBox = new ComboBox();
		comboBox.addItem("DE");
		comboBox.addItem("FI");
		comboBox.addItem("US");
		comboBox.setItemCaption("DE", "Germany");
		comboBox.setItemCaption("FI", "Finland");
		comboBox.setItemCaption("US", "USA");
		comboBox.setImmediate(true);
		// comboBox.setPropertyDataSource(textField);

		addComponent(comboBox);
		Label label2 = new Label();
		label2.setPropertyDataSource(comboBox);
		addComponent(label2);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
