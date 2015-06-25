package com.vaadin.training.fundamentals.exercises.ex10;

import com.vaadin.ui.Label;

@SuppressWarnings("serial")
public class WarningLabel extends Label {

	public WarningLabel(String text) {
		super(text);
		addStyleName("warning-label");
	}

}
