package com.vaadin.training.fundamentals.exercises.ex6;

import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class ProductFormLayout extends FormLayout {

	@PropertyId("name")
	private TextField name = new TextField("Name");

	@PropertyId("price")
	private TextField price = new TextField("Price");

	@PropertyId("options")
	private OptionGroup options = new OptionGroup("Options");

	@PropertyId("available")
	private DateField available = new DateField("Available");

	public ProductFormLayout() {
		options.addItem("Express Shipping");
		options.addItem("Add Gift Card");
		options.addItem("Delivery Notification");
		options.setMultiSelect(true);
		
		price.setConverter(new MyCurrencyConverter());
		
		available.setResolution(Resolution.DAY);

		setSpacing(true);
		addComponents(name, price, options, available);
	}
}
