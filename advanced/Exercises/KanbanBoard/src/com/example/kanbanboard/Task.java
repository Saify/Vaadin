package com.example.kanbanboard;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Label;

public class Task extends CustomComponent {

	private CssLayout taskLayout;

	public Task(String taskDescription) {
		super(new CssLayout());
		taskLayout = (CssLayout) getCompositionRoot();

		taskLayout.addStyleName("task");
		taskLayout.addComponent(new Label(taskDescription));
	}

}
