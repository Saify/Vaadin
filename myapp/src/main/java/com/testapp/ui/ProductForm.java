package com.testapp.ui;

import com.testapp.entity.Product;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;

public class ProductForm extends FormLayout {

	TextField name = new TextField("Product Name ");
	Button save = new Button("Save");
	Button cancel = new Button("Cancel");

	private Product product;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	BeanFieldGroup<Product> formFieldBindings;

	public ProductForm() {
		configureComponents();
	}

	private void configureComponents() {
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		HorizontalLayout buttonsLayout = new HorizontalLayout(save, cancel);
		buttonsLayout.setSpacing(true);
		addComponents(name, buttonsLayout);

		cancel.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				disableProductForm();
			}
		});

		save.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				save(getProduct());
			}
		});
	}

	private void disableProductForm() {
		setEnabled(false);
		formFieldBindings.clear();
	}

	private void save(Product product) {
		if (product != null) {
			try {
				formFieldBindings.commit();
				getMainUI().pService.save(product);
				String saveMessage = String.format(
						"New Product [%s , %s] added", product.getId(),
						product.getName());
				disableProductForm();
				getMainUI().refreshProductsList();
				Notification.show(saveMessage, Type.HUMANIZED_MESSAGE);
			} catch (CommitException e) {
				e.printStackTrace();
			}
		}
	}

	public void edit(Product product) {
		if (product  != null) {
			setProduct(product);
			formFieldBindings = BeanFieldGroup
					.bindFieldsBuffered(product, this);
		}
		setEnabled(product != null);
	}

	private MyUI getMainUI() {
		return (MyUI) super.getUI();
	}
}
