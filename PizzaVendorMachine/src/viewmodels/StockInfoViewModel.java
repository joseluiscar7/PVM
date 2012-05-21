package viewmodels;

public class StockInfoViewModel {
	private String name;
	private int count;
	private float price;
	
	public StockInfoViewModel(String name, int count, float price) {
		super();
		this.name = name;
		this.count = count;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getCount() {
		return count;
	}

	public float getPrice() {
		return price;
	}
	
}
