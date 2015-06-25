package org.vaadin.training.calendarpicker;

import java.util.Date;

import org.vaadin.training.calendarpicker.client.calendarpicker.CaledanrPickerServerRpc;
import org.vaadin.training.calendarpicker.client.calendarpicker.CalendarPickerState;

public class CalendarPicker extends com.vaadin.ui.AbstractField<Date> {

	private CaledanrPickerServerRpc rpc = new CaledanrPickerServerRpc() {

		@Override
		public void setDate(Date newDateValue) {
			if (isReadOnly()) {
				return;
			}

			setValue(newDateValue);
		}

	};

	@Override
	protected void setInternalValue(Date newValue) {
		super.setInternalValue(newValue);
		if (newValue == null) {
			newValue = new Date();
		}
		getState().date = newValue;
	}

	public CalendarPicker() {
		registerRpc(rpc);
	}

	@Override
	public CalendarPickerState getState() {
		return (CalendarPickerState) super.getState();
	}

	@Override
	public Class<? extends Date> getType() {
		return Date.class;
	}
}
