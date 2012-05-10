package models;

public class ToppingStock {
	private int id;
	private PizzaTopping topping;
	private int count;
	public ToppingStock(int id, PizzaTopping topping, int count) {
		super();
		this.id = id;
		this.topping = topping;
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
	public PizzaTopping getTopping() {
		return topping;
	}
}
