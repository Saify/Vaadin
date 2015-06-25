package org.vaadin.training.authentication;

public class AuthenticationService {

	public static User loggedInUser;

	public static User getLoggedInUser() {
		if (loggedInUser == null) {
			loggedInUser = new User();
			loggedInUser.setUserName("johnDoe");
			loggedInUser.setDepartment(new Department());
			loggedInUser.getDepartment().setName("Services");
		}

		return loggedInUser;
	}

}
