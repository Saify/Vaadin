package com.vaadin.training.fundamentals.exercises.ex2;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * This view should have four buttons in a layout aligned horizontally in the
 * following way.
 * 
 * +--------------------------------------------------------------------+ |
 * [Btn1] [Btn2] [Btn3] [Btn4] |
 * +--------------------------------------------------------------------+
 * 
 */
public class NavigationBar extends CustomComponent implements View {

	private static final long serialVersionUID = 1L;

	public NavigationBar() {
		setCompositionRoot(createNavigationLayout());
	}
	
	private Layout createNavigationLayout() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setWidth("100%");

		Button button1 = new Button("Button 1");
		layout.addComponent(button1);
		Button button2 = new Button("Button 2");
		layout.addComponent(button2);
		Button button3 = new Button("Button 3");
		layout.addComponent(button3);
		Button button4 = new Button("Button 4");
		layout.addComponent(button4);
		layout.setExpandRatio(button4, 1);
		layout.setComponentAlignment(button4, Alignment.TOP_RIGHT);
		layout.setSpacing(true);
		return layout;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
