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

	private DropHandler dropHandler = new DropHandler() {

		@Override
		public AcceptCriterion getAcceptCriterion() {
			return new ServerSideCriterion() {

				@Override
				public boolean accept(DragAndDropEvent dragEvent) {
					if (dragEvent.getTargetDetails().getTarget() instanceof DropTargetPanel) {
						Component parent = dragEvent.getTransferable()
								.getSourceComponent().getParent();
						DropTargetPanel dropTarget = (DropTargetPanel) dragEvent
								.getTargetDetails().getTarget();

						return !(todo.getLayout().equals(parent) && dropTarget
								.equals(done));
					} else {
						return false;
					}
				}
			};
		}

		@Override
		public void drop(DragAndDropEvent event) {
			Component component = event.getTransferable().getSourceComponent();
			DropTargetPanel wrapper = ((DropTargetPanel) event
					.getTargetDetails().getTarget());
			VerticalLayout parent = (VerticalLayout) component.getParent();
			parent.removeComponent(component);
			wrapper.addComponent(component);
		}
	};
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
		todo.setDropHandler(dropHandler);
		todo.addComponent(new Task("Implement UI layer"));
		todo.addComponent(new Task("Implement web services"));
		todo.addComponent(new Task("Implement unit tests"));
		todo.addComponent(new Task("Implement integration tests"));
		todo.addComponent(new Task("Write documentation"));
		layout.addComponent(todo);

		ongoing = new DropTargetPanel("Ongoing");
		ongoing.setDropHandler(dropHandler);
		layout.addComponent(ongoing);

		done = new DropTargetPanel("Done");
		done.setDropHandler(dropHandler);
		layout.addComponent(done);

		layout.addStyleName("no-vertical-drag-hints");
		layout.addStyleName("no-horizontal-drag-hints");
	}

}
