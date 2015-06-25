package org.vaadin.training.calendarpicker;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class CalendarpickercomponentUI extends UI {
	@Override
	public void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		
		final CalendarPicker calendarPicker = new CalendarPicker();
		layout.addComponent(calendarPicker);
		
		Button button = new Button("Show value", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				Notification.show(calendarPicker.getDate().toString());				
			}
		});
		layout.addComponent(button);
		
		setContent(layout);
	}

}
