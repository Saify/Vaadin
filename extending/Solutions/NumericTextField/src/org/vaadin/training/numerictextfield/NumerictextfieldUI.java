package org.vaadin.training.numerictextfield;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class NumerictextfieldUI extends UI {
	@Override
	public void init(VaadinRequest request) {
		NumericTextField ntf = new NumericTextField();
		ntf.setImmediate(true);
		ntf.setNullRepresentation("");
		
		setContent(ntf);
	}

}
