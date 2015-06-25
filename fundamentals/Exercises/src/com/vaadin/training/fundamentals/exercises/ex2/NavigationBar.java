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
		Button left1 = new Button("Left1");
		Button left2 = new Button("Left2");
		Button left3 = new Button("Left3");
		Button right1 = new Button("Right1");
		HorizontalLayout navBar = new HorizontalLayout(left1, left2, left3,right1);
		navBar.setComponentAlignment(left3, Alignment.MIDDLE_RIGHT);
		navBar.setComponentAlignment(right1, Alignment.MIDDLE_RIGHT);
		navBar.setWidth("100%");
		navBar.setSpacing(true);
		navBar.setExpandRatio(left3, 1	);
		navBar.setExpandRatio(right1, 1);
		return navBar;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
