package presenters;

import java.util.*;

import models.*;
import rmit.mvvm.*;
import states.*;
import viewmodels.*;

public class DeliveryPresenter extends Presenter {
	private DeliverPizzaState state;
	
	public DeliveryPresenter(DeliverPizzaState state) {
		this.state = state;
	}

	public DeliveryViewModel getViewModel()
	{
		return (DeliveryViewModel)super.getViewModel();
	}
	
	public void load()
	{
		PVMStateContext context = state.getStateContext();
		float price = 0, tax = 0;
		String pizzaInfo = "Base:                  " + context.getPizzaBase().getName() + "\nToppings:\n";
		price += context.getPizzaBase().getPrice();
		tax += context.getPizzaBase().getTax();
		
		PizzaTopping[] toppings = context.getPizzaToppings();
		for(PizzaTopping t : toppings)
		{
			pizzaInfo += "                              " + t.getName() + "\n";
			price += t.getPrice();
			tax += t.getTax();			
		}
		pizzaInfo += "Price (Tax incl):  " + price + "\n";
		pizzaInfo += "Tax:                       " + tax + "\n";
		pizzaInfo += "Amount Paid:      " + context.getPaidAmount() + "\n";
		pizzaInfo += "Change:               " + (context.getPaidAmount() - price);
		getViewModel().setPizzaInfo(pizzaInfo);
		getViewModel().setCookTime(context.getPizzaBase().getCookMinutes());
	}
	
	@BindEvent(name="Return")
	public void onReturn(Object[] args)
	{
		getViewModel().setExitView(true);
		state.stop();
	}
}
