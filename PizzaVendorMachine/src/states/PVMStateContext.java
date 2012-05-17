package states;

import models.*;
import rmit.utils.workflow.StateContext;

public class PVMStateContext implements StateContext {
	private boolean cancelPayment = false;
	private PizzaBase pizzaBase;
	private PizzaTopping[] pizzaToppings;
	private int vendorID = 0;
	private float price;
	private float tax;

	public boolean isCancelPayment() {
		return cancelPayment;
	}

	public void setCancelPayment(boolean cancelPayment) {
		this.cancelPayment = cancelPayment;
	}

	public PizzaBase getPizzaBase() {
		return pizzaBase;
	}

	public void setPizzaBase(PizzaBase pizzaBase) {
		this.pizzaBase = pizzaBase;
	}

	public PizzaTopping[] getPizzaToppings() {
		return pizzaToppings;
	}

	public void setPizzaToppings(PizzaTopping[] pizzaToppings) {
		this.pizzaToppings = pizzaToppings;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}

	public float getTax() {
		return tax;
	}

	public void setTax(float tax) {
		this.tax = tax;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
}
