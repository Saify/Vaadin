package com.vaadin.training.fundamentals.exercises.ex1;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.training.fundamentals.exercises.ex1.LongRunningOperation.FinishEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class Polling extends VerticalLayout implements View,
		LongRunningOperation.FinishedListener {
	private static final long serialVersionUID = 1L;
	private boolean isRunning = false;
	private Label statusLabel;
	private ProgressBar progressIndicator;

	public Polling() {
		progressIndicator = new ProgressBar();
		progressIndicator.setIndeterminate(true);
		progressIndicator.setVisible(false);

		statusLabel = new Label("Not started");
		statusLabel.setSizeUndefined();

		addComponent(createStartButton());
		addComponent(createAsyncStartButton());
		addComponent(createShowNotificationButton());
		addComponent(progressIndicator);
		addComponent(statusLabel);
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
		Button startButton = new Button("Start", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				startOperation(false);
			}
		});
		startButton.setWidth("150px");
		return startButton;
	}

	/**
	 * Implement a button which will start the long running operation
	 * asynchronously.
	 * 
	 * @return
	 */
	private Button createAsyncStartButton() {
		Button startAsyncButton = new Button("Start Async",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						startOperation(true);
					}
				});
		startAsyncButton.setWidth("150px");
		return startAsyncButton;
	}

	/**
	 * Implement a button which shows the text "Hello world" in a notification.
	 * 
	 * @return
	 */
	private Button createShowNotificationButton() {
		Button showNotification = new Button("Show notification",
				new ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						Notification.show("Hello");
					}
				});
		showNotification.setWidth("150px");
		return showNotification;
	}

	/**
	 * Starts an operation which will run for 5 seconds.
	 * 
	 * @param isAsync
	 *            If true, then the operation is ran asynchronously in a
	 *            separate thread. Otherwise the operation is ran in the same
	 *            thread.
	 */
	private void startOperation(boolean isAsync) {
		if (!isRunning) {
		        UI.getCurrent().setPollInterval(200);
			isRunning = true;
			statusLabel.setValue("Running");
			LongRunningOperation longRunningOperation = new LongRunningOperation();
			longRunningOperation.addListener(Polling.this);
			progressIndicator.setVisible(true);
			if (isAsync) {
				longRunningOperation.startAsync();
			} else {
				longRunningOperation.start();
			}
		}
	}

	public void finished(FinishEvent event) {
	    getUI().access(new Runnable() {

	        @Override
                public void run() {
	            statusLabel.setValue("Finished");
                    isRunning = false;
                    progressIndicator.setVisible(false);
                    UI.getCurrent().setPollInterval(-1);
                }
            });
	}
	
}
