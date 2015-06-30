package com.vaadin.training.fundamentals.exercises.ex6;

import java.util.Calendar;
import java.util.HashSet;

import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
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
import com.vaadin.ui.VerticalLayout;

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
		VerticalLayout editLayout = new VerticalLayout();
		editLayout.setMargin(true);
		editLayout.setSizeFull();

		final FieldGroup fieldGroup = new FieldGroup(item);

		ProductFormLayout productFormLayout = new ProductFormLayout();

		editLayout.addComponent(productFormLayout);

		fieldGroup.bindMemberFields(productFormLayout);

		HorizontalLayout actions = new HorizontalLayout();
		actions.setSpacing(true);
		actions.addComponent(new Button("Save", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				// Save
				try {
					fieldGroup.commit();
				} catch (CommitException e) {
					e.printStackTrace();
				}
			}
		}));

		actions.addComponent(new Button("Cancel", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				// Cancel
				fieldGroup.discard();
			}
		}));

		productFormLayout.addComponent(actions);
		return editLayout;
	}

	private Layout createViewLayout(Item item) {
		FormLayout layout = new FormLayout();

		Label name = new Label();
		name.setPropertyDataSource(item.getItemProperty("name"));
		name.setCaption("Name");
		layout.addComponent(name);

		Label price = new Label();
		price.setCaption("Price");
		price.setPropertyDataSource(item.getItemProperty("price"));
		layout.addComponent(price);

		Label options = new Label();
		options.setCaption("Options");
		options.setConverter(new CollectionToStringConverter());
		options.setPropertyDataSource(item.getItemProperty("options"));
		layout.addComponent(options);

		Label available = new Label();
		available.setCaption("Available");
		available.setPropertyDataSource(item.getItemProperty("available"));
		layout.addComponent(available);

		return layout;
	}

	@Override
	public void enter(ViewChangeEvent event) {

	}

	private static Item createItem() {
		Product product = new Product();
		product.setName("");
		product.setOptions(new HashSet<String>());
		product.setAvailable(Calendar.getInstance().getTime());
		return new BeanItem<Product>(product);
	}

}
