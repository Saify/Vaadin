package com.vaadin.training.fundamentals.exercises.ex5;

import com.vaadin.data.Validator;
import com.vaadin.data.util.converter.ConverterUtil;
import com.vaadin.data.util.converter.StringToDoubleConverter;
import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.DoubleRangeValidator;
import com.vaadin.data.validator.EmailValidator;
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

		TextField email = new TextField("Email");
		EmailValidator ev = new EmailValidator("Please enter a correct email!");
		email.addValidator(ev);
		email.setImmediate(true);

		TextField doubleValue = new TextField("Double Value Range Validator");
		doubleValue.setInputPrompt("Enter value between 10.4 - 15.7");
		doubleValue.setColumns(20);
		doubleValue.setNullRepresentation("");
		doubleValue.setConverter(new StringToDoubleConverter());
		doubleValue.addValidator(new DoubleRangeValidator(doubleValue
				.getValue() + " Value Out Of Range", 10.4, 15.7));
		doubleValue.setImmediate(true);

		TextField stringLength = new TextField(
				"String Field With Length Validator");
		stringLength.setColumns(25);
		stringLength.setInputPrompt("Maximum Characters allowed 10");
		stringLength.setImmediate(true);
		stringLength.addValidator(new StringLengthValidator(
				"Allowed String Length is 10", 0, 10, true));

		// Custom Validator

		TextField customValidator = new TextField("Field With Custom Validator");
		customValidator.setImmediate(true);

		Validator myValidator = new AbstractValidator<String>(
				"\"{0}\" Value Not Accepted") {

			@Override
			protected boolean isValidValue(String value) {
				if (value == null || "".equals(value)) {
					return true;
				} else if (value != null && "Vaadin".equals(value)) {
					return true;
				}
				return false;
			}

			@Override
			public Class<String> getType() {
				return String.class;
			}
		};
		customValidator.addValidator(myValidator);
		customValidator.setInputPrompt("Only value \"Vaadin\" is accepted");
		customValidator.setColumns(20);
		mainLayout.addComponents(email, doubleValue, stringLength,
				customValidator);

	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
