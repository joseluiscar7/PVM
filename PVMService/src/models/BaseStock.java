package models;

public class BaseStock {
	private int vendorId;
	private PizzaBase base;
	private int count;
	public BaseStock(int vendorId, PizzaBase base, int count) {
		super();
		this.vendorId = vendorId;
		this.base = base;
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
	public PizzaBase getBase() {
		return base;
	}
}
