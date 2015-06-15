package com.testapp.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class ProductForm extends FormLayout implements ClickListener {
	TextField name = new TextField("Product Name ");
	Button save = new Button("Save");

	public ProductForm() {
		addComponent(name);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
	}
}
