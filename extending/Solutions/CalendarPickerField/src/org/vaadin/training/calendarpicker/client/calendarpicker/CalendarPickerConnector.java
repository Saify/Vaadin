package org.vaadin.training.calendarpicker.client.calendarpicker;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.vaadin.training.calendarpicker.CalendarPicker.class)
public class CalendarPickerConnector extends AbstractFieldConnector implements
		ValueChangeHandler<Date> {

	public CalendarPickerConnector() {
		getWidget().addValueChangeHandler(this);
	}

	@Override
	protected Widget createWidget() {
		return GWT.create(DatePicker.class);
	}

	@Override
	public DatePicker getWidget() {
		return (DatePicker) super.getWidget();
	}

	@Override
	public CalendarPickerState getState() {
		return (CalendarPickerState) super.getState();
	}

	@Override      
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		if (stateChangeEvent.hasPropertyChanged("date")) {
		    Date newDate = getState().date;
		    getWidget().setValue(newDate);
		    getWidget().setCurrentMonth(newDate);
		}
	}

	@Override
	public void onValueChange(ValueChangeEvent<Date> date) {
		// RPC class instance is cached in a map, no overhead here except map lookup
		getRpcProxy(CaledanrPickerServerRpc.class).setDate(
				date.getValue());
	}
}
