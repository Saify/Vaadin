package com.testapp.ui;

import javax.servlet.annotation.WebServlet;

import com.testapp.controller.ProductService;
import com.testapp.entity.Product;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.testapp.MyAppWidgetset")
public class MyUI extends UI {

	Button addNew = new Button("Add");
	Button delete = new Button("Delete");
	Grid productsGrid = new Grid();
	ProductForm productForm = new ProductForm();

	ProductService pService = ProductService.ProductService();

	@Override
	protected void init(VaadinRequest vaadinRequest) {

		HorizontalLayout productLayout = new HorizontalLayout();
		HorizontalLayout buttonsLayout = new HorizontalLayout(addNew, delete);
		buttonsLayout.setSpacing(true);

		VerticalLayout left = new VerticalLayout(buttonsLayout, productsGrid);

		productLayout.addComponents(left, productForm);
		left.setMargin(true);
		left.setSpacing(true);
		setContent(productLayout);

		configureComponents();
	}

	private void configureComponents() {
		addNew.setStyleName(ValoTheme.BUTTON_PRIMARY);
		delete.setEnabled(false);
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		productForm.setEnabled(false);

		productsGrid.setContainerDataSource(new BeanItemContainer<>(
				Product.class));
		productsGrid.setSelectionMode(SelectionMode.SINGLE);
		productsGrid.addSelectionListener(e -> toggleEditDeleteItem());
		delete.addClickListener(e -> deleteProduct(getSelectedProdcut()));
		addNew.addClickListener(e -> addProduct());
		refreshProductsList();
	}

	public void cancelSelectedProduct() {
		productsGrid.select(null);
		toggleEditDeleteItem();
	}

	private void toggleEditDeleteItem() {
		if (getSelectedProdcut() != null) {
			delete.setEnabled(true);
		} else {
			delete.setEnabled(false);
		}
		productForm.edit(getSelectedProdcut());
	}

	private void deleteProduct(Product selectedProduct) {
		pService.delete(selectedProduct);
		refreshProductsList();
		toggleEditDeleteItem();
	}
	
	private void addProduct(){
		productForm.edit(new Product());
	}

	private Product getSelectedProdcut() {
		return (Product) productsGrid.getSelectedRow();
	}

	public void refreshProductsList() {
		productsGrid.setContainerDataSource(new BeanItemContainer<>(
				Product.class, pService.findProducts()));
	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
}
