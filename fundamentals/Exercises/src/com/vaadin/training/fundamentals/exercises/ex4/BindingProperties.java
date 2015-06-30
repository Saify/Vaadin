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

		// TODO: Create a slider and add it to the layout
		Slider slider = new Slider("Volume", 0, 100);
		slider.setWidth("500px");
		// TODO: Create a label that show's the slider's value
		Label sliderValue = new Label();
		sliderValue.setValue("40");
		slider.setPropertyDataSource(sliderValue);
		ProgressBar sliderBar = new ProgressBar();
		sliderBar.setCaption("Buffering...");
		sliderBar.setWidth("500px");
		sliderBar.setConverter(new Converter<Float, Double>() {

			@Override
			public Double convertToModel(Float value,
					Class<? extends Double> targetType, Locale locale)
					throws com.vaadin.data.util.converter.Converter.ConversionException {
				return new Double(value * 100);
			}

			@Override
			public Float convertToPresentation(Double value,
					Class<? extends Float> targetType, Locale locale)
					throws com.vaadin.data.util.converter.Converter.ConversionException {
				return new Float(value / 100f);
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
		
		sliderBar.setPropertyDataSource(slider);

		sliderBar.setPropertyDataSource(slider);

		addComponents(slider, sliderValue, sliderBar);

		setMargin(true);
		setSpacing(true);
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
