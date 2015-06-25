package com.vaadin.training.fundamentals.exercises.ex1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class LongRunningOperation implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<FinishedListener> listeners = new ArrayList<FinishedListener>();

	public void start() {
		new Task().run();
	}

	public void startAsync() {
		new Thread(new Task()).start();
	}

	public void addListener(FinishedListener listener) {
		synchronized (listeners) {
			listeners.add(listener);
		}
	}

	public class Task implements Runnable {
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (listeners) {
				for (FinishedListener listener : listeners) {
					listener.finished(new FinishEvent(
							LongRunningOperation.this));
				}
			}
		}
	}

	public interface FinishedListener {
		void finished(FinishEvent event);
	}

	public class FinishEvent extends EventObject {

		private static final long serialVersionUID = 1L;

		public FinishEvent(Object source) {
			super(source);
		}
	}

}
