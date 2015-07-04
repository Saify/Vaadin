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
	 * name | sector | ----------+-------------------+ John Doe | Private
	 * customer | Vaadin | Company | EFF | Organization |
	 * ----------+-------------------+
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private IndexedContainer createContainer() {
		
		IndexedContainer ic = new IndexedContainer();
		ic.addContainerProperty("productName", String.class, null);
		ic.addContainerProperty("price", Double.class, null);

		Item p1 = ic.addItem(1);
		p1.getItemProperty("productName").setValue("Logitech G27");
		p1.getItemProperty("price").setValue(999.02);

		Item p2 = ic.addItem(2);
		p2.getItemProperty("productName").setValue("Nissan 350z");
		p2.getItemProperty("price").setValue(37000.00);
		
		Item p3 = ic.addItem(3);
		p3.getItemProperty("productName").setValue("Kit Kar");
		p3.getItemProperty("price").setValue(2.5);

		return ic;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

}
