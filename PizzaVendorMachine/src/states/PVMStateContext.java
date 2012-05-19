package states;

import models.*;
import rmit.utils.workflow.StateContext;
import services.PizzaService;

public class PVMStateContext implements StateContext {
	private PizzaBase pizzaBase;
	private PizzaTopping[] pizzaToppings;
	private Vendor vendor;
	private float price;
	private float tax;
	private float paidAmount;
	private PizzaService pizzaService;

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	public float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(float paidAmount) {
		this.paidAmount = paidAmount;
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

	public PizzaService getPizzaService() {
		return pizzaService;
	}

	public void setPizzaService(PizzaService pizzaService) {
		this.pizzaService = pizzaService;
	}
}
