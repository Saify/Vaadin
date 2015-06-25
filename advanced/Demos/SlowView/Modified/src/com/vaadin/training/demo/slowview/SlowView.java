package com.vaadin.training.demo.slowview;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class SlowView extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4473078890669127635L;

	private boolean initialzied = false;

	public SlowView(String caption) {
		setCaption(caption);
	}

	public void buildView() {
		if (initialzied) {
			return;
		}
		initialzied = true;

		addComponent(new Label("This is a slow view to initialize."));
		// This thread sleep simulates a heavy process in the view, for example,
		// the populating of lots of containers that are used in forms.
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
