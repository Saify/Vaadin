package com.example.webstoreshoppingcart;

import java.util.List;

import backend.Backend;

import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Field;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import data.ShoppingItem;
import data.User;

@SuppressWarnings({ "unchecked", "serial" })
public class ShoppingCartView extends VerticalLayout {

	private static final String PROPERTY_TOTALPRICE = "totalprice";
	private static final String PROPERTY_UNITPRICE = "unitprice";
	private static final String PROPERTY_QUANTITY = "quantity";
	private static final String PROPERTY_PRODUCT = "product";
	private Table table;
	private User user;

	public ShoppingCartView() {
		setSizeUndefined();
		user = ShoppingCartUI.getCurrent().getUser();

		addComponent(new Label("Shopping cart for " + user.getUsername()));

		Table table = createShoppingCart();
		addComponent(table);
	}

	private Table createShoppingCart() {
		table = new Table();

		table.addContainerProperty(PROPERTY_PRODUCT, String.class, "");
		table.addContainerProperty(PROPERTY_QUANTITY, Field.class, 0);
		table.addContainerProperty(PROPERTY_UNITPRICE, Double.class, 0.0);
		table.addContainerProperty(PROPERTY_TOTALPRICE, Double.class, 0.0);

		List<ShoppingItem> shoppingCart = Backend.getShoppingCartFor(user);
		table.setPageLength(shoppingCart.size());
		for (ShoppingItem shoppingItem : shoppingCart) {
			Item item = table.addItem(shoppingItem);
			item.getItemProperty(PROPERTY_PRODUCT).setValue(
					shoppingItem.getProduct());
			item.getItemProperty(PROPERTY_QUANTITY).setValue(
					createQuantityField(shoppingItem));
			item.getItemProperty(PROPERTY_UNITPRICE).setValue(
					shoppingItem.getUnitPrice());
			item.getItemProperty(PROPERTY_TOTALPRICE).setValue(
					shoppingItem.getTotalPrice());
		}
		return table;
	}

	private TextField createQuantityField(final ShoppingItem shoppingItem) {
		TextField field = new TextField();
		field.setImmediate(true);
		final Property<Integer> fieldProperty = new ObjectProperty<Integer>(
				shoppingItem.getQuantity(), Integer.class);
		field.setPropertyDataSource(fieldProperty);
		field.addValueChangeListener(new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				Integer newQuantity = fieldProperty.getValue();
				quantityChangedFor(shoppingItem, newQuantity);
			}

		});
		return field;
	}

	private void quantityChangedFor(final ShoppingItem shoppingItem,
			Integer newQuantity) {
		Backend.setQuantity(shoppingItem.getId(), newQuantity);
		double itemTotalPrice = Backend.getTotalPriceFor(shoppingItem.getId());
		table.getItem(shoppingItem).getItemProperty(PROPERTY_TOTALPRICE)
				.setValue(itemTotalPrice);
		double totalPrice = Backend.getShoppingCartTotalPriceFor(user);
		JavaScript.eval("updateTotalPrice(" + totalPrice + ");");
	}
}
