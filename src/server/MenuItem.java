package server;

/**
 * Represents a single item on the menu with its respective attributes.
 * 
 * @author Karimshan Nawaz
 *
 */
public class MenuItem {
	
	/**
	 * An enumeration of our menu items.
	 * Syntax for ingredients: each ingredient separated by "," and 
	 * the colon represents the amount of that ingredient
	 */
	public enum MenuItems {
		
		JUICY_LUCY("Juicy Lucy", 9.50, "A deliciously juicy burger", 1140, "wheat, gluten", 0,
				"beef_patty:2,burger_buns:1,cheese:1"),
		SVN_WNDRS_BRGR("Seven Wonders Burger", 7.00, "Our specialty in-house burger!", 980, "wheat, gluten", 0,
				"beef_patty:1,burger_buns:1,cheese:1,onion:3"),
		CHEESE_BRGR("Cheeseburger", 6.75, "Try our classic cheeseburger with a warm bun and melted american cheese",
				740, "wheat, gluten", 0, "beef_patty:1,burger_buns:1,cheese:1"),
		SALAD("Tossed Salad", 6.50, "Vegan, allergen and vegetarian friendly option with ranch dressing", 350,
				null, 2, "lettuce:5,tomatoes:5,ranch:1,cucumber:5"),
		WINGS("Classic Fried Wings", 8.99, "Try with choice of buffalo, bbq or chipotle sauce!", 1000,
				null, 0, "wings:10"),
		SHAKE("Blended Milkshake", 4.99, 
				"Our creamy milkshake will have you coming back for more! Try with your choice of chocolate, vanilla or strawberry", 
				480, "milk, eggs", 1, "shake:1"),
		COKE("Coke", 3.50, "The classic drink that America loves", 300, null, 0, "drink_coke:1");
		
		private String name;
		private double price;
		private String description;
		private int calories;
		private String allergens;
		private int type; // indicates if the menu item is vegan (2), vegetarian (1) or neither (0).
		private String ingredients;
		
		private MenuItems(String name, double price, String description, int calories, String allergens,
				int type, String ingredients) {
			this.name = name;
			this.price = price;
			this.description = description;
			this.calories = calories;
			this.allergens = allergens;
			this.type = type;
			this.ingredients = ingredients;
		}
	}

}
