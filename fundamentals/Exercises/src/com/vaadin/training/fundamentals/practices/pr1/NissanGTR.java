package com.vaadin.training.fundamentals.practices.pr1;

public class NissanGTR extends Car {

	private static int MAX_SPEED = 280;

	private static int ALRET_SPEED = 200;

	private static int SPEED_INCREASE_CONSTANT = 50;

	public NissanGTR() {
		setCarName("Nissan GTR");
		setCarAlertSpeed(ALRET_SPEED);
		setCarMaxSpeed(MAX_SPEED);
		setCarSpeedConstant(SPEED_INCREASE_CONSTANT);
	}
}
