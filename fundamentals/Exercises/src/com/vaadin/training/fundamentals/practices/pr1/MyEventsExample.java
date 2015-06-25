package com.vaadin.training.fundamentals.practices.pr1;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class MyEventsExample extends VerticalLayout implements View {

	public MyEventsExample() {
		addComponent(createStartButton());
		addComponent(createAsyncStartButton());
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	/**
	 * Implement a button which will start the long running operation
	 * synchronously.
	 * 
	 * @return
	 */
	private Button createStartButton() {
		// TODO
		Button sync = new Button("Happy", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

			}
		});
		sync.setWidth("150px");
		return sync;
	}

	/**
	 * Implement a button which will start the long running operation
	 * asynchronously.
	 * 
	 * @return
	 */
	private Button createAsyncStartButton() {
		// TODO
		Button async = new Button("Angry", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {

			}
		});
		async.setWidth("150px");
		return async;
	}

	private void changeMood() {

	}

}
