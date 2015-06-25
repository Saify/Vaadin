package org.vaadin.training.views.department;

import org.vaadin.training.data.Person;

public class EmployeeProxy {

	private Person person;

	public EmployeeProxy(Person person) {
		this.person = person;
	}

	public String getFirstName() {
		return getPerson().getFirstName();
	}

	public void setFirstName(String firstName) {
		getPerson().setFirstName(firstName);
	}

	public String getLastName() {
		return getPerson().getLastName();
	}

	public void setLastName(String lastName) {
		getPerson().setLastName(lastName);
	}

	public String getEmail() {
		return getPerson().getEmail();
	}

	public void setEmail(String email) {
		getPerson().setEmail(email);
	}

	public Double getSalary() {
		return person.getSalary();
	}

	public void setSalary(Double salary) {
		if (salary == null){
			person.setSalary(0d);
		}else {
			person.setSalary(salary);
		}
	}

	public Person getPerson() {
		return person;
	}

	public Integer getId() {
		return person.getId();
	}

}
