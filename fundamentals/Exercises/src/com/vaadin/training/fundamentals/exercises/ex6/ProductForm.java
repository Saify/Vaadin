package com.vaadin.training.fundamentals.exercises.ex6;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;

@SuppressWarnings("serial")
public class ProductForm extends CustomComponent implements View {

	public ProductForm() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();
		setCompositionRoot(horizontalLayout);

		final Item item = createItem();
		horizontalLayout.addComponent(createEditLayout(item));
		horizontalLayout.addComponent(createViewLayout(item));
	}

	private Layout createEditLayout(Item item) {
		// TODO Create a layout for editing the product.
		// TODO Create a FieldGroup that can modify a Product object
		// TODO Create Save and Cancel buttons which will commit/discard the
		// values in the FieldGroup.
		return null;
	}

	private Layout createViewLayout(Item item) {
		FormLayout layout = new FormLayout();

		Label name = new Label();
		// TODO Bind the name property to this label
		name.setCaption("Name");
		layout.addComponent(name);

		Label price = new Label();
		price.setCaption("Price");
		// TODO Bind the price property to this label
		layout.addComponent(price);

		Label options = new Label();
		options.setCaption("Options");
		// TODO Bind the options property to this label. Since options is a Set,
		// you'll need to use the collection converter provided.
		layout.addComponent(options);

		Label available = new Label();
		available.setCaption("Available");
		// TODO Bind the available property to this label
		layout.addComponent(available);

		return layout;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	private static Item createItem() {
		Product product = new Product();
		product.setName("");
		product.setOptions(new HashSet<String>(Arrays.asList("First")));
		product.setAvailable(Calendar.getInstance().getTime());
		return new BeanItem<Product>(product);
	}

}
