package com.vaadin.training.fundamentals.practices.pr1;

public class Race {

	public static void main(String[] args) {
		Car nissanGTR = new NissanGTR();
		SpeedCheckTracker speedTracker = new SpeedCheckTracker();
		speedTracker.addSpeedCheckListener(nissanGTR);
		speedTracker.checkCarsSpeed(nissanGTR);
		Car toyotaSupra = new ToyotaSupra();
		speedTracker.addSpeedCheckListener(toyotaSupra);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(nissanGTR);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(nissanGTR);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(nissanGTR);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(nissanGTR);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(nissanGTR);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(toyotaSupra);
		speedTracker.checkCarsSpeed(nissanGTR);

	}

}
