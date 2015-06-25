package org.vaadin.training.optimization;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class OptimizationUI extends UI {

	@Override
	public void init(VaadinRequest request) {
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);

		for (int i = 0; i < 100; i++) {
			Panel personPanel = new Panel();
			personPanel.setCaption("John Doe");

			layout.addComponent(personPanel);

			HorizontalLayout hl = new HorizontalLayout();
			hl.setMargin(true);
			personPanel.setContent(hl);
			hl.setWidth("100%");

			VerticalLayout contactDetails = new VerticalLayout();
			Label phone = new Label();
			phone.setValue("Phone: 123 4567");
			contactDetails.addComponent(phone);
			Label fax = new Label();
			fax.setValue("Fax: 123 4568");
			contactDetails.addComponent(fax);
			Label email = new Label();
			email.setValue("Email: example@example.com");
			contactDetails.addComponent(email);
			hl.addComponent(contactDetails);

			VerticalLayout addressLayout = new VerticalLayout();
			Label address = new Label();
			address.setValue("Random street 1");
			addressLayout.addComponent(address);
			Label city = new Label();
			city.setValue("City of Lorem Ipsum");
			addressLayout.addComponent(city);
			Label zip = new Label();
			zip.setValue("012345");
			addressLayout.addComponent(zip);
			hl.addComponent(addressLayout);

		}

		setContent(layout);
	}
}

