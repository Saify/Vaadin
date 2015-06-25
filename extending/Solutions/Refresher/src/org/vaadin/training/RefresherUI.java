package org.vaadin.training;

import java.util.Timer;
import java.util.TimerTask;

import org.vaadin.training.Refresher.PollListener;

import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Main UI class
 */
@SuppressWarnings("serial")
public class RefresherUI extends UI {

	private int counter = 0;
	private Label label;
	private int polls = 0;

	@Override
	protected void init(VaadinRequest request) {

		// <TODO>
		final Refresher refresher = new Refresher();
		addExtension(refresher);
		refresher.addPollListener(new PollListener() {

			@Override
			public void poll() {
				synchronized (label) {
					polls++;
					setLabelValue();
				}
			}
		});
		// </TODO>

		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		label = new Label();
		setLabelValue();

		layout.addComponent(label);

		final TextField interval = new TextField("Interval (ms)");
		interval.setValue("1000");
		layout.addComponent(interval);

		Button button = new Button("Enable refresher");
		// <TODO>
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				refresher.setEnabled(true);
				refresher.setInterval(Integer.parseInt(interval.getValue()));
			}
		});
		// </TODO>
		layout.addComponent(button);

		Button disable = new Button("Disable refresher");
		disable.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				refresher.setEnabled(false);
			}
		});
		layout.addComponent(disable);

		new Thread() {
			public void run() {
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new Task(), 0, 500);
			}
		}.run();

	}

	private void setLabelValue() {
		label.setValue("The timer has changed me " + counter
				+ " times. Refresher has polled " + polls + " times");
	}

	class Task extends TimerTask {
		public void run() {
			synchronized (label) {
				try {
					VaadinSession.getCurrent().lock();
					counter++;
					setLabelValue();
					// this.cancel();
				} finally {
					VaadinSession.getCurrent().unlock();
				}
			}
		}
	}
}
