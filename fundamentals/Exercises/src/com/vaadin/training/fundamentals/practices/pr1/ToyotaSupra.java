package com.vaadin.training.fundamentals.practices.pr1;

public class ToyotaSupra extends Car {

	private static int MAX_SPEED = 220;

	private static int ALRET_SPEED = 170;

	private static int SPEED_INCREASE_CONSTANT = 30;

	public ToyotaSupra() {
		setCarName("Toyota Supra");
		setCarAlertSpeed(ALRET_SPEED);
		setCarMaxSpeed(MAX_SPEED);
		setCarSpeedConstant(SPEED_INCREASE_CONSTANT);
	}
}
