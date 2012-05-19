package models;

import java.util.Date;

public class Order {
	private int id;
	private Date orderTime;
	private PizzaBase pizzaBase;
	private PizzaTopping[] pizzaToppings;
	private Vendor vendor;
	private float amount;
	
	private void setValues(int id, Date orderTime, PizzaBase pizzaBase,
			PizzaTopping[] pizzaToppings, Vendor vendor) {
		this.id = id;
		this.orderTime = orderTime;
		this.pizzaBase = pizzaBase;
		this.pizzaToppings = pizzaToppings;
		this.vendor = vendor;
		this.amount = pizzaBase.getPrice();
		for(PizzaTopping t : pizzaToppings)
		{
			 this.amount += t.getPrice();
		}
	}
	public Order(PizzaBase pizzaBase,
			PizzaTopping[] pizzaToppings, Vendor vendor) {
		setValues(0, new Date(), pizzaBase, pizzaToppings, vendor);
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
	public PizzaTopping[] getPizzaToppings() {
		return pizzaToppings;
	}
	public Vendor getVendor() {
		return vendor;
	}
	public float getAmount() {
		return amount;
	}
}
