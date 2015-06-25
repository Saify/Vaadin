package com.vaadin.training.fundamentals.practices.pr1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpeedCheckTracker {

	private List<SpeedCheckListener> speedCheckListeners = new ArrayList<SpeedCheckListener>();

	private List<SpeedCheckListener> getSpeedCheckListeners() {
		return speedCheckListeners;
	}

	public synchronized void addSpeedCheckListener(SpeedCheckListener l) {
		speedCheckListeners.add(l);
	}

	public synchronized void removeSpeedCheckListener(SpeedCheckListener l) {
		speedCheckListeners.remove(l);
	}

	public void checkCarsSpeed(Car car) {
		if ((car.getCurrentSpeed() + car.getCarSpeedConstant()) < car
				.getCarMaxSpeed()) {
			car.setCurrentSpeed(car.getCurrentSpeed()
					+ car.getCarSpeedConstant());
		} else {
			car.setCurrentSpeed(car.getCarMaxSpeed());
		}
		fireSpeedCheckEvent();
	}

	private synchronized void fireSpeedCheckEvent() {
		SpeedCheckEvent event = new SpeedCheckEvent(this);
		Iterator<SpeedCheckListener> listeners = getSpeedCheckListeners()
				.iterator();
		while (listeners.hasNext()) {
			((SpeedCheckListener) listeners.next()).notifyCarSpeed(event);

		}
	}
}
