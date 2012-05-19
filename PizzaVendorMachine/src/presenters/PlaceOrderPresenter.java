package presenters;

import java.util.*;

import models.*;
import rmit.mvvm.*;
import states.*;
import viewmodels.*;

public class PlaceOrderPresenter extends Presenter {
	private OrderPizzaState state;
	private Coin[] coins;
	
	public PlaceOrderPresenter(OrderPizzaState state) {
		this.state = state;
	}

	public PlaceOrderViewModel getViewModel()
	{
		return (PlaceOrderViewModel)super.getViewModel();
	}
	
	public void load()
	{
		PVMStateContext context = state.getStateContext();
		coins = context.getVendor().getCountry().getCoins();
		context.setPaidAmount(0);
		List coinsName = new ArrayList();
		for(Coin c : coins)
		{
			coinsName.add(c.getName());
		}
		getViewModel().setCoinList(coinsName);
		getViewModel().setOwingFee(context.getPrice());
	}
	
	@BindEvent(name="Cancel")
	public void onCancel(Object[] args)
	{
		getViewModel().setExitView(true);
		state.stop();
	}
	
	@BindEvent(name="SelectCoin")
	public void onSelectCoin(Object[] args)
	{
		PVMStateContext context = state.getStateContext();
		int index = (Integer)args[0];
		float currentValue = context.getPaidAmount() + coins[index].getValue();
		context.setPaidAmount(currentValue);
		getViewModel().setOwingFee(context.getPrice() - currentValue);
		if (currentValue >= context.getPrice())
		{
			Order order = new Order(context.getPizzaBase(), context.getPizzaToppings(), context.getVendor());
			context.getPizzaService().placeOrder(order);
			getViewModel().setExitView(true);
			state.stop();
		}
		
	}
}
