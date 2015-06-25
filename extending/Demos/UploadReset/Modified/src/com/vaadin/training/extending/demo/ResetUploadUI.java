package com.vaadin.training.extending.demo;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ResetUploadUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ResetUploadUI.class, widgetset="com.vaadin.training.extending.demo.Widgetset")
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Upload u = new Upload();
		final ResetUploadExtension reset = new ResetUploadExtension(u);
		Button resetButton = new Button("Reset", new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				reset.resetUpload();
			}
		});
		
		layout.addComponents(u, resetButton);
	}

}
