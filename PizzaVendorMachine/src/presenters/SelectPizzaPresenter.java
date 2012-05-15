package presenters;

import java.util.*;

import models.*;

import rmit.mvvm.*;
import services.PizzaService;
import states.SelectPizzaState;
import viewmodels.SelectPizzaViewModel;

public class SelectPizzaPresenter extends Presenter {
	private SelectPizzaState selectPizzaState;
	private PizzaService pizzaService;
	
	public SelectPizzaPresenter(SelectPizzaState selectPizzaState, PizzaService pizzaService) {
		this.selectPizzaState = selectPizzaState;
		this.pizzaService = pizzaService;
	}

	public SelectPizzaViewModel getViewModel()
	{
		return (SelectPizzaViewModel)super.getViewModel();
	}
	
	public void load()
	{
		Vendor vendor = pizzaService.getVendorById(1);
		List<String> pizzaBaseList = new ArrayList();
		List<BaseStock> baseList = vendor.getBaseStockList();
		for(BaseStock bs : baseList)
		{
			pizzaBaseList.add(bs.getBase().getName());
		}
		
		List<String> pizzaToppingList = new ArrayList();
		List<ToppingStock> toppingList = vendor.getToppingStockList();
		for(ToppingStock ts : toppingList)
		{
			pizzaToppingList.add(ts.getTopping().getName());
		}

		getViewModel().setPizzaBaseList(pizzaBaseList);
		getViewModel().setPizzaToppingList(pizzaToppingList);
	}
	
	@BindEvent(name="SelectPizzaBase")
	public void onSelectPizzaBase(Object[] args)
	{
		System.out.println((String)args[0]);
	}
	
	@BindEvent(name="SelectPizzaToppings")
	public void onSelectPizzaToppings(Object[] args)
	{
		System.out.println("toppings selected");
	}
	
	@BindEvent(name="Confirm")
	public void onConfirm(Object[] args)
	{
		getViewModel().setExitView(true);
		selectPizzaState.stop();
	}
}
