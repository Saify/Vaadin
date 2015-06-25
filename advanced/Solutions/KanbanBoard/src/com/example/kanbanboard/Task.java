package com.example.kanbanboard;

import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DragAndDropWrapper;
import com.vaadin.ui.Label;

public class Task extends DragAndDropWrapper {
	
	private CssLayout taskLayout;
	private String taskDescription;

	public Task(String taskDescription) {
		super(new CssLayout());
		this.taskDescription = taskDescription;
		taskLayout = (CssLayout) getCompositionRoot();
		
		taskLayout.addStyleName("task");
		taskLayout.addComponent(new Label(taskDescription));
		setDragStartMode(DragStartMode.WRAPPER);
	}
	
	@Override
	public String toString() {
		return taskDescription;
	}

}
