package states;

import models.*;
import rmit.utils.workflow.StateContext;

public class PVMStateContext implements StateContext {
	private boolean cancelPayment = false;
	private PizzaBase pizzaBase;
	private PizzaTopping[] pizzaToppings;

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
	
}
