package com.testapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.testapp.entity.Product;

public class ProductService {

	// Create dummy data by randomly combining first and last names
	static String[] pNames = { "MacBook Pro", "Logitech G27", "M&M", "LG G3","Nissan GTR" };

	private static ProductService instance;
	private static long nextId = 0;
	private static HashMap<Long, Product> products = new HashMap<>();

	public static ProductService ProductService() {
		if (instance == null) {
			final ProductService pService = new ProductService();
			for (int i = 0; i < pNames.length; i++) {
				// Add default Products
				Product product = new Product();
				product.setName(pNames[i]);
				pService.save(product);
			}
			System.err.println(products.size());
			instance = pService;
		}
		return instance;
	}

	public long count() {
		return products.size();
	}

	public void delete(Product value) {
		products.remove(value.getId());
	}

	public synchronized void save(Product entry) {
		if (entry.getId() == null) {
			entry.setId(++nextId);
		}

		try {
			entry = (Product) BeanUtils.cloneBean(entry);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		products.put(entry.getId(), entry);
	}

	public List<Product> findProducts() {
		ArrayList<Product> foundProducts = new ArrayList<>();
		for (Product product : products.values()) {
			foundProducts.add(product);
		}
		return foundProducts;
	}
}
