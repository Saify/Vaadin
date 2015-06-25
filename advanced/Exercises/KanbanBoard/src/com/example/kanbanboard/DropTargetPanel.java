package com.example.kanbanboard;

import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class DropTargetPanel extends CustomComponent {

	private VerticalLayout content = new VerticalLayout();

	public DropTargetPanel(String caption) {
		super(new Panel());
		setSizeFull();
		getCompositionRoot().setSizeFull();
		getCompositionRoot().setCaption(caption);
		content.setWidth("100%");
		content.setMargin(true);
		content.setSpacing(true);
		((Panel) getCompositionRoot()).setContent(content);
	}

	public void addComponent(Task task) {
		content.addComponent(task);

	}

}
