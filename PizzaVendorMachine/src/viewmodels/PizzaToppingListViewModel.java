package viewmodels;

public class PizzaToppingListViewModel {
	private String name;
	private float price;
	private int stock;
	
	public PizzaToppingListViewModel(String name, float price, int stock) {
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public String getName() {
		return name;
	}
	public float getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
}
