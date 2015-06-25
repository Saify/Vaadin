package com.example.webstoreshoppingcart;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

import data.User;

@SuppressWarnings("serial")
public class ShoppingCartUI extends UI {

	@WebServlet(value = { "/ShoppingCart/*", "/VAADIN/*" }, asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ShoppingCartUI.class)
	public static class Servlet extends VaadinServlet {
	}

	private User user;

	@Override
	@SuppressWarnings("unchecked")
	protected void init(VaadinRequest request) {
		readSessionData();
		if (noUserLoggedIn()) {
			setContent(new Label("Please log in"));
		} else {
			setContent(new ShoppingCartView());
		}
	}

	public static ShoppingCartUI getCurrent() {
		return (ShoppingCartUI) UI.getCurrent();
	}

	public User getUser() {
		return user;
	}

	private void readSessionData() {
		user = (User) VaadinSession.getCurrent().getSession()
				.getAttribute("user");
	}

	private boolean noUserLoggedIn() {
		return getUser() == null || getUser().getUsername() == null;
	}

}
