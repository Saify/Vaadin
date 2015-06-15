package com.testapp.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.testapp.MyAppWidgetset")
public class MyUI extends UI {

	Button addNew = new Button("Add");
	Button edit = new Button("Edit");
	Button delete = new Button("Delete");
	Grid products = new Grid();
	
	@Override
	protected void init(VaadinRequest vaadinRequest) {

		VerticalLayout productLayout = new VerticalLayout();
		HorizontalLayout buttonsLayout = new HorizontalLayout(addNew, edit,
				delete);
		buttonsLayout.setSpacing(true);
		productLayout.addComponents(buttonsLayout,products);
		productLayout.setMargin(true);
		productLayout.setSpacing(true);
		setContent(productLayout);
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
