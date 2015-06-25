package com.example.webstoreshoppingcart;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
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
		setSizeUndefined();
		setContent(new ShoppingCartView());
	}
}
