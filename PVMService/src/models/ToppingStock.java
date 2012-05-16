package models;

public class ToppingStock {
	private int vendorId;
	private PizzaTopping topping;
	private int count;
	public ToppingStock(int vendorId, PizzaTopping topping, int count) {
		super();
		this.vendorId = vendorId;
		this.topping = topping;
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getVendorId() {
		return vendorId;
	}
	public PizzaTopping getTopping() {
		return topping;
	}
}
