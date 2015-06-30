package com.vaadin.training.fundamentals.exercises.ex6;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

public class MyCurrencyConverter implements Converter<String, Double> {

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

	@Override
	public Class<Double> getModelType() {
		return Double.class;
	}

	@Override
	public String convertToPresentation(Double value,
			Class<? extends String> targetType, Locale locale)
			throws ConversionException {
		return String.format(Locale.US, "%1$.3f QR", value);
	}

	@Override
	public Double convertToModel(String value,
			Class<? extends Double> targetType, Locale locale)
			throws ConversionException {

		return Double.parseDouble(removeCurrencySymbols(value));
	}

	public static String removeCurrencySymbols(String value) {
		String valueWithoutSymbol = value;
		if (value.endsWith("QR")) {
			valueWithoutSymbol = value.substring(0, value.length() - 2).trim();
		} 
		return valueWithoutSymbol;
	}

}
