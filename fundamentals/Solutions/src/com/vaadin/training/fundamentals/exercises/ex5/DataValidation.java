package com.vaadin.training.fundamentals.exercises.ex5;

import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.StringToDoubleConverter;
import com.vaadin.data.util.converter.StringToIntegerConverter;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.IntegerRangeValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class DataValidation extends CustomComponent implements View {

	private static final long serialVersionUID = 1L;

	public DataValidation() {
		FormLayout mainLayout = new FormLayout();
		mainLayout.setSizeUndefined();
		setCompositionRoot(mainLayout);

		Validator validator = new DoubleRangeValidator(
				"Value must be a Double between 1 and 100", 1d, 100d);
		TextField doubleField = new TextField("Double range validator");
		doubleField.setImmediate(true);
		doubleField.addValidator(validator);
		doubleField.setConverter(new StringToDoubleConverter());
		doubleField.setNullRepresentation("");
		mainLayout.addComponent(doubleField);

		validator = new IntegerRangeValidator(
				"Value must be an Integer and between 1 and 100", 1, 100);
		TextField integerField = new TextField("Integer range validator");
		integerField.setImmediate(true);
		integerField.addValidator(validator);
		integerField.setConverter(new StringToIntegerConverter());
		integerField.setNullRepresentation("");
		mainLayout.addComponent(integerField);

		validator = new EmailValidator(
				"Are you sure the given value is an email address");
		TextField emailField = new TextField("Email validator");
		emailField.setImmediate(true);
		emailField.addValidator(validator);
		mainLayout.addComponent(emailField);

		validator = new StringLengthValidator(
				"Maximum of 10 characters allowed", 0, 10, true);
		TextField stringField = new TextField("String length validator");
		stringField.setImmediate(true);
		stringField.addValidator(validator);
		mainLayout.addComponent(stringField);

		validator = new AbstractValidator<String>("{0} not accepted") {

			private static final long serialVersionUID = 1L;

			@Override
			protected boolean isValidValue(String value) {
				if(value == null || "".equals(value)) {
					return true;
				}
				
				if (value != null && value.equals("Vaadin")) {
					return true;
				}
				return false;
			}

			@Override
			public Class<String> getType() {
				return String.class;
			}

		};
		TextField vaadinField = new TextField("Vaadin validator");
		vaadinField.setImmediate(true);
		vaadinField.addValidator(validator);
		mainLayout.addComponent(vaadinField);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
