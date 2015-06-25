package org.vaadin.training.numerictextfield.client.numerictextfield;

import java.util.ArrayList;
import java.util.List;

import org.vaadin.training.numerictextfield.NumericTextField;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.vaadin.client.ui.VTextField;
import com.vaadin.client.ui.textfield.TextFieldConnector;
import com.vaadin.shared.ui.Connect;

@Connect(NumericTextField.class)
public class NumericTextFieldConnector extends TextFieldConnector {

	
private List<Integer> acceptedKeyCodes = new ArrayList<Integer>();
	
	public NumericTextFieldConnector() {
		acceptedKeyCodes.add((int) '0');
		acceptedKeyCodes.add((int) '1');
		acceptedKeyCodes.add((int) '2');
		acceptedKeyCodes.add((int) '3');
		acceptedKeyCodes.add((int) '4');
		acceptedKeyCodes.add((int) '5');
		acceptedKeyCodes.add((int) '6');
		acceptedKeyCodes.add((int) '7');
		acceptedKeyCodes.add((int) '8');
		acceptedKeyCodes.add((int) '9');
		acceptedKeyCodes.add(KeyCodes.KEY_BACKSPACE);
		acceptedKeyCodes.add(KeyCodes.KEY_DELETE);
		acceptedKeyCodes.add(KeyCodes.KEY_LEFT);
		acceptedKeyCodes.add(KeyCodes.KEY_RIGHT);

		getWidget().addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if (!acceptedKeyCodes.contains(event.getNativeKeyCode())) {
					event.preventDefault();
				}
			}
		});
	}

	@Override
	protected VTextField createWidget() {
		return GWT.create(VTextField.class);
	}

	@Override
	public VTextField getWidget() {
		return (VTextField) super.getWidget();
	}

}

