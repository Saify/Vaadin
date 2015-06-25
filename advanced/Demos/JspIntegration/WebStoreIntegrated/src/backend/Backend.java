package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import data.ShoppingItem;
import data.User;

public class Backend {

	private static List<ShoppingItem> shoppingCart = new ArrayList<ShoppingItem>();
	private static Random random = new Random();
	static {
		List<String> products = Arrays.asList("Banana", "Milk", "Bread",
				"Coffee", "Cheese", "Meat");
		int id = 0;
		for (String product : products) {
			ShoppingItem newItem = new ShoppingItem();
			newItem.setProduct(product);
			newItem.setQuantity(random.nextInt(5) + 1);
			newItem.setUnitPrice(createRandomUnitPrice());
			newItem.setId(id++);
			shoppingCart.add(newItem);
		}

	}

	private static double createRandomUnitPrice() {
		return Math.round(1000000.0 * (random.nextDouble() * 10 + 0.2)) / 1000000.0;
	}

	public static synchronized List<ShoppingItem> getShoppingCartFor(User user) {
		return shoppingCart;
	}

	public static synchronized double getShoppingCartTotalPriceFor(User user) {
		double price = 0;
		for (ShoppingItem item : shoppingCart) {
			price += item.getTotalPrice();
		}
		return Math.round(price * 100.0) / 100.0;
	}

	public static synchronized void setQuantity(int shoppingItemId, int quantity) {
		shoppingCart.get(shoppingItemId).setQuantity(quantity);
	}

	public static synchronized double getTotalPriceFor(int shoppingItemId) {
		return shoppingCart.get(shoppingItemId).getTotalPrice();
	}

}
