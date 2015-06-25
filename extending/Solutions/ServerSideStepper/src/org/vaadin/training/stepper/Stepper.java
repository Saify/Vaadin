package org.vaadin.training.stepper;

import org.vaadin.training.numerictextfield.NumericTextField;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;

public class Stepper extends CustomField<Integer> implements ClickListener {

	private static final long serialVersionUID = -5149721942124950318L;

	private NumericTextField numericTextField = new NumericTextField();
	private Button stepUp = new Button("^", this);
	private Button stepDown = new Button("V", this);

	@Override
	protected Component initContent() {
		setValue(0);
		HorizontalLayout layout = new HorizontalLayout();		
		layout.addComponent(numericTextField);		
		numericTextField.setPropertyDataSource(this);
		numericTextField.setImmediate(true);
				
		stepUp.setWidth("40px");
		layout.addComponent(stepUp);
		
		stepDown.setWidth("40px");
		layout.addComponent(stepDown);
		layout.setExpandRatio(numericTextField, 1);
		
		return layout;
	}

	@Override
	public Class<? extends Integer> getType() {
		return Integer.class;
	}

	@Override
	public void buttonClick(ClickEvent event) {
		if (event.getButton().equals(stepUp)) {
			increaseValueByOne();
		} else {
			decreaseValueByOne();
		}
	}

	private void increaseValueByOne() {
		Integer value = getValue();
		value++;
		setValue(value);
	}

	private void decreaseValueByOne() {
		Integer value = getValue();
		value--;
		setValue(value);
	}
	
}
