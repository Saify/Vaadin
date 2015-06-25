package org.vaadin.training.calendarpicker;

import java.util.Date;

import org.vaadin.training.calendarpicker.client.calendarpicker.CalendarPickerServerRpc;
import org.vaadin.training.calendarpicker.client.calendarpicker.CalendarPickerState;

public class CalendarPicker extends com.vaadin.ui.AbstractComponent {

	private CalendarPickerServerRpc rpc = new CalendarPickerServerRpc() {

		@Override
		public void setDate(Date newValue) {
			getState().date = newValue;
		}

	};

	public CalendarPicker() {
		registerRpc(rpc);
	}

	@Override
	public CalendarPickerState getState() {
		return (CalendarPickerState) super.getState();
	}
	
	public Date getDate() {
		return ((CalendarPickerState)getState(false)).date;
	}
}
