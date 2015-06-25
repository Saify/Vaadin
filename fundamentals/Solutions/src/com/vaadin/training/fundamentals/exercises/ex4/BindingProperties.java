package com.vaadin.training.fundamentals.exercises.ex4;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Slider;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class BindingProperties extends VerticalLayout implements View {

	public BindingProperties() {
		setSpacing(true);

		Slider slider = new Slider(
				"On a scale from 0 to 100, how much do you like Vaadin?");
		slider.setMax(100d);
		slider.setMin(0d);
		slider.setWidth("300px");
		addComponent(slider);

		Label label = new Label();
		label.setPropertyDataSource(slider);
		addComponent(label);

		ProgressBar bar = new ProgressBar();
		bar.setWidth("300px");
		bar.setConverter(new Converter<Float, Double>() {

			@Override
			public Double convertToModel(Float value,
					Class<? extends Double> targetType, Locale locale)
					throws com.vaadin.data.util.converter.Converter.ConversionException {
				return (double) (value*100);
			}

			@Override
			public Float convertToPresentation(Double value,
					Class<? extends Float> targetType, Locale locale)
					throws com.vaadin.data.util.converter.Converter.ConversionException {
				return (float) (value/100f);
			}

			@Override
			public Class<Double> getModelType() {
				return Double.class;
			}

			@Override
			public Class<Float> getPresentationType() {
				return Float.class;
			}
		});
		bar.setPropertyDataSource(slider);
		addComponent(bar);

		setMargin(true);
		setSpacing(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
