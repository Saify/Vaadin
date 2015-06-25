package org.vaadin.training.stepper;

import org.vaadin.training.stepper.client.stepper.StepperServerRpc;
import org.vaadin.training.stepper.client.stepper.StepperState;

public class Stepper extends com.vaadin.ui.AbstractField<Integer> {

	private static final long serialVersionUID = 1L;
	private StepperServerRpc rpc = new StepperServerRpc() {

		private static final long serialVersionUID = 1L;

		@Override
		public void setValue(Integer value) {
			if (isReadOnly()) {
				return;
			}

			Stepper.this.setValue(value);
		}

	};

	public Stepper() {
		registerRpc(rpc);
		setInternalValue(0);
	}

	@Override
	public StepperState getState() {
		return (StepperState) super.getState();
	}

	@Override
	public Class<? extends Integer> getType() {
		return Integer.class;
	}

	protected void setInternalValue(Integer newValue) {
		super.setInternalValue(newValue);
		getState().value = newValue;
	}
}
