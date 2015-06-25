package com.vaadin.training.fundamentals.exercises.ex8;

import java.util.Calendar;
import java.util.Random;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.training.fundamentals.exercises.ex8.Product;

public class ContainerHelper {

	private final static int numItems = 1000;

	public static BeanItemContainer<Product> createProductContainer() {
		return createContainer(Product.class, new ProductBeanCreator());
	}

	private static <T> BeanItemContainer<T> createContainer(
			Class<? super T> type, BeanCreator<T> creator) {
		BeanItemContainer<T> container = new BeanItemContainer<T>(type);

		for (int i = 0; i < numItems; i++) {
			container.addBean(creator.createItem());
		}
		return container;
	}

	private abstract static class BeanCreator<T> {
		public abstract T createItem();
	}

	private static class ProductBeanCreator extends BeanCreator<Product> {

		private final Random rand = new Random(1001L);
		private final static String NAMES = "Knife Scythe Sickle Hammer Maul Screwdriver Saw Pliers Wrench";
		private String[] productNames;

		public ProductBeanCreator() {
			productNames = NAMES.split(" ");
		}

		@Override
		public Product createItem() {
			Product product = new Product();
			Calendar cal = Calendar.getInstance();
			cal.set(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH) + rand.nextInt(2), rand.nextInt(25));
			product.setAvailable(cal.getTime());
			product.setName(productNames[rand.nextInt(productNames.length - 1)]);
			product.setPrice(rand.nextInt(40));
			return product;
		}
	}
}
