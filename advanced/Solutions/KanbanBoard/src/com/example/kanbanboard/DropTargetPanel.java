package com.example.kanbanboard;

import com.vaadin.ui.Component;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class DropTargetPanel extends DragAndDropWrapper {

	private VerticalLayout content = new VerticalLayout();

	public DropTargetPanel(String caption) {
		super(new Panel());
		setSizeFull();
		getCompositionRoot().setSizeFull();
		getCompositionRoot().setCaption(caption);
		content.setWidth("100%");
		content.setMargin(true);
		content.setSpacing(true);
		content.addStyleName("no-box-drag-hints");
		((Panel) getCompositionRoot()).setContent(content);
	}

	public void addComponent(Component component) {
		content.addComponent(component);
	}

	public void removeComponent(Component component) {
		content.removeComponent(component);
	}

	public Component getLayout() {
		return content;
	}

	@Override
	public String toString() {
		return getCompositionRoot().getCaption();
	}

}
