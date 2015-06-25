package com.example.containertraining;

import javax.servlet.annotation.WebServlet;

import com.example.containertraining.backend.DataProviderImpl;
import com.example.containertraining.container.MyContainer;
import com.example.containertraining.data.Contact;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Container;
import com.vaadin.data.Container.Hierarchical;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Table;
import com.vaadin.ui.TreeTable;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ContainertrainingUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = ContainertrainingUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		MyContainer<Contact> container = new MyContainer<Contact>(
				new DataProviderImpl(), new Contact(0));
		Table t = resolveTableComponent(container);
		t.setImmediate(true);
		t.setSizeFull();
		t.setContainerDataSource(container);

		Object[] cols = { "firstName", "lastName", "title", "street", "city",
				"phone" };
		t.setVisibleColumns(cols);
		layout.addComponent(t);
	}

	private Table resolveTableComponent(Container container) {
		if (container instanceof Hierarchical) {
			return new TreeTable();
		}
		return new Table();
	}
}
