package com.vaadin.training.fundamentals.practices.pr1;

public class Car implements SpeedCheckListener {
	private static int MAX_SPEED = 280;

	private static int ALRET_SPEED = 250;

	private static int SPEED_INCREASE_CONSTANT = 30;

	private int currentSpeed;

	private int carSpeedConstant;

	private int carMaxSpeed;

	private int carAlertSpeed;

	private String carName;

	public int getCarMaxSpeed() {
		return carMaxSpeed;
	}

	public void setCarMaxSpeed(int carMaxSpeed) {
		this.carMaxSpeed = carMaxSpeed;
	}

	public int getCarAlertSpeed() {
		return carAlertSpeed;
	}

	public void setCarAlertSpeed(int carAlertSpeed) {
		this.carAlertSpeed = carAlertSpeed;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	@Override
	public void notifyCarSpeed(SpeedCheckEvent speedEvent) {
		if (getCurrentSpeed() < getCarAlertSpeed()) {
			System.err.println("Speed Increased " + getCarName() + " Speed "
					+ getCurrentSpeed() + "KM/H");
		} else if ((getCurrentSpeed() < getCarMaxSpeed())
				&& getCurrentSpeed() >= getCarAlertSpeed()) {
			System.err
					.println("Speed Increased " + getCarName()
							+ "  Slow Down, Car Speed Is " + getCurrentSpeed()
							+ "KM/H");
		} else {
			System.out.println(getCarName() + "  Max Speed Reached "
					+ getCurrentSpeed() + "KM/H");
		}
	}

	public int getCarSpeedConstant() {
		return carSpeedConstant;
	}

	public void setCarSpeedConstant(int carSpeedConstant) {
		this.carSpeedConstant = carSpeedConstant;
	}
}
