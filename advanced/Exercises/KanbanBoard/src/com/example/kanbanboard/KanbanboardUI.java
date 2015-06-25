package com.example.kanbanboard;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.ServerSideCriterion;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("kanbanboard")
@SuppressWarnings("serial")
public class KanbanboardUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = KanbanboardUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private DropTargetPanel todo;
	private DropTargetPanel done;
	private DropTargetPanel ongoing;

	@Override
	protected void init(VaadinRequest request) {
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		layout.setSizeFull();

		todo = new DropTargetPanel("TODO");
		todo.addComponent(new Task("Implement UI layer"));
		todo.addComponent(new Task("Implement web services"));
		todo.addComponent(new Task("Implement unit tests"));
		todo.addComponent(new Task("Implement integration tests"));
		todo.addComponent(new Task("Write documentation"));
		layout.addComponent(todo);

		ongoing = new DropTargetPanel("Ongoing");
		layout.addComponent(ongoing);

		done = new DropTargetPanel("Done");
		layout.addComponent(done);
	}

}
