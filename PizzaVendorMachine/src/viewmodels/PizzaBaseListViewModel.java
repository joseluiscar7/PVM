package viewmodels;

public class PizzaBaseListViewModel {
	private String name;
	private float price;
	private int stock;
	
	public PizzaBaseListViewModel(String name, float price, int stock) {
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
