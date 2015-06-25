package org.vaadin.training.stepper;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

public class ServersidestepperUI extends UI {
	@Override
	public void init(VaadinRequest request) {
		Label label = new Label("Hello Vaadin user");
		VerticalLayout layout = new VerticalLayout();
		
		Stepper stepper = new Stepper();
		layout.addComponent(stepper);
		layout.addComponent(label);
		label.setPropertyDataSource(stepper);
		setContent(layout);
	}

}
