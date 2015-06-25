package org.vaadin.training.optimization;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

@Theme("optimizationtheme")
public class OptimizationUI extends UI {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void init(VaadinRequest request) {		
		CssLayout uiRootLayout = new CssLayout();
		uiRootLayout.setStyleName("ui-layout");

		for (int i = 0; i < 100; i++) {
			CssLayout hl = new CssLayout();
			hl.setStyleName("person-panel");
			hl.setCaption("John Doe");
			hl.setWidth("100%");

			Label phone = new Label(
					"Phone: 123 4567<br />Fax: 123 4568<br />Email: example@example.com",
					ContentMode.HTML);
			phone.setWidth("50%");
			hl.addComponent(phone);

			Label address = new Label(
					"Random street 1<br/>City of Lorem Ipsum<br />012345",
					ContentMode.HTML);
			address.setWidth("50%");
			hl.addComponent(address);
			uiRootLayout.addComponent(hl);
		}

		setContent(uiRootLayout);
	}

}
