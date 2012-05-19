package views;

import java.io.IOException;
import java.util.*;

import rmit.mvvm.*;
import thinlet.*;
import viewmodels.*;

public class SelectPizzaView extends View {
	private Thinlet thinlet;
	private FrameLauncher frameLauncher;
	
	public SelectPizzaViewModel getViewModel()
	{
		return (SelectPizzaViewModel)super.getViewModel();
	}
	
	public SelectPizzaView() {
		thinlet = new Thinlet();
		try {
			thinlet.add(thinlet.parse("/UIDefinition/SelectPizza.xml", this));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		frameLauncher = new FrameLauncher("Select Pizza", thinlet, 320, 240);
	}
	
	@BindValue(name="PizzaBaseList")
	public void updatePizzaBaseList()
	{
		Object ltPizzaBase = thinlet.find("pizzaBase");
		thinlet.removeAll(ltPizzaBase);
		List<PizzaBaseListViewModel> pizzaBaseList = getViewModel().getPizzaBaseList();
		for(PizzaBaseListViewModel base : pizzaBaseList)
		{
			Object item = Thinlet.create("item");
			thinlet.setString(item, "text", base.getName());
			if (base.getStock() == 0)
				thinlet.setBoolean(item, "enabled", false);
			thinlet.add(ltPizzaBase, item);
		}
	}
	
	@BindValue(name="PizzaToppingList")
	public void PizzaToppingList()
	{
		Object ltPizzaTopping = thinlet.find("pizzaTopping");
		thinlet.removeAll(ltPizzaTopping);
		List<PizzaToppingListViewModel> pizzaToppingList = getViewModel().getPizzaToppingList();
		for(PizzaToppingListViewModel topping : pizzaToppingList)
		{
			Object item = Thinlet.create("item");
			thinlet.setString(item, "text", topping.getName());
			if (topping.getStock() == 0)
				thinlet.setBoolean(item, "enabled", false);			
			thinlet.add(ltPizzaTopping, item);
		}
	}
	
	@BindValue(name="ExitView")
	public void exitView()
	{
		System.out.println("exit");
		frameLauncher.dispose();
	}
	
	
	@BindValue(name="Price")
	public void updatePrice()
	{
		Object lbPrice = thinlet.find("price");
		thinlet.setString(lbPrice, "text", "Price:  " + getViewModel().getPrice() + " " + getViewModel().getCurrency());
	}

	
	public void selectPizzaBase(Object list)
	{
		int itemIndex = thinlet.getSelectedIndex(list);
		Object item = thinlet.getItem(list, itemIndex);
		if (!thinlet.getBoolean(item, "enabled"))
		{
			thinlet.setBoolean(item, "selected", false);
			itemIndex = -1;
		}
		getViewModel().eventSelectPizzaBase(itemIndex);
		
	}
	
	public void selectPizzaToppings(Object list)
	{
		Object[] items = thinlet.getSelectedItems(list);
		List allItems = Arrays.asList(thinlet.getItems(list));
		List<Integer> values = new ArrayList();
		for(Object item : items)
		{
			if (thinlet.getBoolean(item, "enabled"))
				values.add(allItems.indexOf(item));
			else
			{
				thinlet.setBoolean(item, "selected", false);
			}				
		}
		getViewModel().eventSelectPizzaToppings(values.toArray());
	}
	
	public void clickConfirm()
	{
		getViewModel().eventConfirm();
	}
}
