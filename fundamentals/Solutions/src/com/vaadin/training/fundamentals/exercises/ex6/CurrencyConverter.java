package com.vaadin.training.fundamentals.exercises.ex6;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

@SuppressWarnings("serial")
public class CurrencyConverter implements Converter<String, Double> {

	public static String removeCurrencySymbols(String value) {
		String valueWithoutSymbol = value;
		if (value.endsWith("e")) {
			valueWithoutSymbol = value.substring(0, value.length() - 1).trim();
		} else if (value.endsWith("EUR")) {
			valueWithoutSymbol = value.substring(0, value.length() - 3).trim();
		}
		return valueWithoutSymbol;
	}

	@Override
	public Class<Double> getModelType() {
		return Double.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

	@Override
	public Double convertToModel(String value,
			Class<? extends Double> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return Double.parseDouble(removeCurrencySymbols(value));
	}

	@Override
	public String convertToPresentation(Double value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return String.format(Locale.US, "%1$.2f e", value);
	}

}
