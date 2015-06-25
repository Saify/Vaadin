package org.vaadin.training.jstimer;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class JavaScriptTimerUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = JavaScriptTimerUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		// create root layout
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		// add timer component
		final TimerComponent timer=new TimerComponent();
		layout.addComponent(timer);
		// add buttons
		HorizontalLayout buttons=new HorizontalLayout();
		buttons.setSpacing(true);
		// add start button
		Button start=new Button("start");
		start.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				timer.start();
			}
		});
		buttons.addComponent(start);
		// add stop button
		Button stop=new Button("stop");
		stop.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				timer.stop();
			}
		});
		buttons.addComponent(stop);
		// add reset button
		Button reset=new Button("reset");
		reset.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				timer.reset();
			}
		});
		buttons.addComponent(reset);
		layout.addComponent(buttons);
	}

}
