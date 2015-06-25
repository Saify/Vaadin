package com.vaadin.training.fundamentals.exercises.ex7;

import com.vaadin.data.Item;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class PopulateContainer extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public PopulateContainer() {
		Table table = new Table();
		table.setContainerDataSource(createContainer());
		table.setImmediate(true);
		table.setSelectable(true);
		table.addValueChangeListener(new ValueChangeListener() {

			private static final long serialVersionUID = 1L;

			public void valueChange(ValueChangeEvent event) {
				if (event.getProperty().getValue() != null)
					Notification
							.show(event.getProperty().getValue().toString());
			}
		});

		addComponent(table);
	}

	/**
	 * Creates a container containing three items
	 * 
	 * name 	 | sector			 | 
	 * ----------+-------------------+ 
	 * John Doe  | Private customer  |
	 * Vaadin  	 | Company			 | 
	 * EFF	 	 | Organization 	 | 
	 * ----------+-------------------+
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private IndexedContainer createContainer() {
		IndexedContainer container = new IndexedContainer();
		container.addContainerProperty("name", String.class, null);
		container.addContainerProperty("sector", String.class, null);

		Item privateCustomer = container.addItem(1);
		privateCustomer.getItemProperty("name").setValue("John Doe");
		privateCustomer.getItemProperty("sector").setValue("Private customer");

		Item companyCustomer = container.addItem(2);
		companyCustomer.getItemProperty("name").setValue("Vaadin");
		companyCustomer.getItemProperty("sector").setValue("Company");

		Item organizationCustomer = container.addItem(3);
		organizationCustomer.getItemProperty("name").setValue("EFF");
		organizationCustomer.getItemProperty("sector").setValue("Organization");

		return container;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
