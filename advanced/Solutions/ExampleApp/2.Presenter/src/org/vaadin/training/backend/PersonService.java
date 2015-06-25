package org.vaadin.training.backend;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.vaadin.training.data.Person;

public class PersonService {

	private static String[] firstName = { "James", "John", "Robert", "Michael",
			"William", "David", "Richard", "Charles", "Joseph", "Christopher",
			"Mary", "Patricia", "Linda", "Barbara", "Elizabeth", "Jennifer",
			"Maria", "Susan", "Margaret", "Dorothy", "Lisa" };

	private static String[] lastName = { "Smith", "Johnson", "Williams",
			"Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor",
			"Andreson", "Thomas", "Jackson", "White" };

	private static List<Person> persons;

	// Simulated delay of the service. Time given in milliseconds.
	private static boolean DELAY = true;

	private final ExecutorService pool = Executors.newFixedThreadPool(10);

	public ProgressingFuture<List<Person>> getEmployeesAsync() {
		return new ProgressingFuture<List<Person>>() {
			{
				setFuture(pool.submit(new Callable<List<Person>>() {

					@Override
					public List<Person> call() throws Exception {
						if (DELAY) {
							for (int i = 1; i <= 100; i++) {
								setProgress(i);
								try {
									// sleep between 20 and 150 ms
									long sleep = (long) (Math.random() * 1000L % 30) + 20;
									Thread.sleep(sleep);
								} catch (InterruptedException e) {
									Thread.currentThread().interrupt();
								}
							}

						}

						return getEmployees();
					}
				}));
			}
		};
	}

	public synchronized List<Person> getEmployees() {
		if (persons == null) {
			persons = new ArrayList<Person>();
			for (int i = 0; i < 100; i++) {
				Person person = new Person();
				person.setId(i);
				int firstNameIndex = (int) Math.round(Math.random() * 10000)
						% firstName.length;
				person.setFirstName(firstName[firstNameIndex]);
				int lastNameIndex = (int) Math.round(Math.random() * 10000)
						% lastName.length;
				person.setLastName(lastName[lastNameIndex]);

				person.setEmail(person.getFirstName().toLowerCase() + "."
						+ person.getLastName().toLowerCase() + "@example.com");

				persons.add(person);
			}
		}
		AuditLogService.addAuditLogEntry("Personel data fetched");
		return persons;
	}

	public void save(Person person) {
		// This simulates the bean being saved. Do nothing.
		AuditLogService.addAuditLogEntry(person.getFirstName() + " "
				+ person.getLastName() + "'s details updated");
	}

}
