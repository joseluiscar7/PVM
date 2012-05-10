package models;

import java.util.Date;

public class Order {
	private int id;
	private Date orderTime;
	private PizzaBase pizzaBase;
	private PizzaTopping pizzaTopping;
	private Vendor vendor;
	private float amount;
	
	private void setValues(int id, Date orderTime, PizzaBase pizzaBase,
			PizzaTopping pizzaTopping, Vendor vendor) {
		this.id = id;
		this.orderTime = orderTime;
		this.pizzaBase = pizzaBase;
		this.pizzaTopping = pizzaTopping;
		this.vendor = vendor;
		this.amount = pizzaBase.getPrice() + pizzaTopping.getPrice();
	}
	
	Order(Date orderTime, PizzaBase pizzaBase,
			PizzaTopping pizzaTopping, Vendor vendor) {
		setValues(-1, orderTime, pizzaBase, pizzaTopping, vendor);
	}
	
	public Order(int id, Date orderTime, PizzaBase pizzaBase,
			PizzaTopping pizzaTopping, Vendor vendor) {
		setValues(id, orderTime, pizzaBase, pizzaTopping, vendor);
	}
	
	public int getId() {
		return id;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public PizzaBase getPizzaBase() {
		return pizzaBase;
	}
	public PizzaTopping getPizzaTopping() {
		return pizzaTopping;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public float getAmount() {
		return amount;
	}
}
