package org.vaadin.training.calendarpicker;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class CalendarpickerfieldUI extends UI {
	@Override
	public void init(VaadinRequest request) {
		CalendarPicker calendarPicker = new CalendarPicker();
		
		DateField dateField = new DateField();
		dateField.setImmediate(true);
		calendarPicker.setPropertyDataSource(dateField);
		
		HorizontalLayout layout = new HorizontalLayout();
		layout.addComponent(calendarPicker);
		layout.addComponent(dateField);
		
		setContent(layout);
	}

}
