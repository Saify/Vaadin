package com.example.containertraining.data;

public class Contact {

	private final int id;
	private String firstName;
	private String lastName;
	private String street;
	private String city;
	private String phone;
	private String title;

	public Contact(int id) {
		this.id = id;
	}

	public Contact(int id, String firstName, String lastName, String street,
			String city, String phone, String title) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.phone = phone;
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
