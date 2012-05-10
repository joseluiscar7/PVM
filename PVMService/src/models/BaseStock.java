package models;

public class BaseStock {
	private int id;
	private PizzaBase base;
	private int count;
	public BaseStock(int id, PizzaBase base, int count) {
		super();
		this.id = id;
		this.base = base;
		this.count = count;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public PizzaBase getBase() {
		return base;
	}
}
