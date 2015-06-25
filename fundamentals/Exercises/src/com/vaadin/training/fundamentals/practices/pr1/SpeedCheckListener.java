package com.vaadin.training.fundamentals.practices.pr1;

import java.util.EventListener;

public interface SpeedCheckListener extends EventListener {

	void notifyCarSpeed(SpeedCheckEvent speedEvent);
}
