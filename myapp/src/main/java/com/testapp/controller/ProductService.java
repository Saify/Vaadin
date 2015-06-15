package com.testapp.controller;

public class ProductService {

	// Create dummy data by randomly combining first and last names
	static String[] pNames = { "Peter", "Alice", "John", "Mike", "Olivia",
			"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene", "Lisa",
			"Linda", "Timothy", "Daniel", "Brian", "George", "Scott",
			"Jennifer" };

	private static ProductService instance;

	public ProductService() {
		if (instance == null) {
			ProductService pService = new ProductService();
			for (int i = 0; i < 10; i++){
				//Add default Products
				
			}
				instance = pService;
		}
	}

}
