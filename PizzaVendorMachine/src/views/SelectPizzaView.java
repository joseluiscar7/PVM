package views;

import java.io.IOException;
import java.util.*;

import rmit.mvvm.BindValue;
import rmit.mvvm.View;
import thinlet.FrameLauncher;
import thinlet.Thinlet;
import viewmodels.SelectPizzaViewModel;

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
		List<String> pizzaBaseList = getViewModel().getPizzaBaseList();
		for(String base : pizzaBaseList)
		{
			Object item = Thinlet.create("item");
			thinlet.setString(item, "text", base);
			thinlet.add(ltPizzaBase, item);
		}
	}
	
	@BindValue(name="PizzaToppingList")
	public void PizzaToppingList()
	{
		Object ltPizzaTopping = thinlet.find("pizzaTopping");
		thinlet.removeAll(ltPizzaTopping);
		List<String> pizzaToppingList = getViewModel().getPizzaToppingList();
		for(String topping : pizzaToppingList)
		{
			Object item = Thinlet.create("item");
			thinlet.setString(item, "text", topping);
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
		thinlet.setString(lbPrice, "text", "Price:  " + getViewModel().getPrice());
	}

	
	public void selectPizzaBase(Object list)
	{
		int itemIndex = thinlet.getSelectedIndex(list);
		getViewModel().eventSelectPizzaBase(itemIndex);
	}
	
	public void selectPizzaToppings(Object list)
	{
		Object[] items = thinlet.getSelectedItems(list);
		List allItems = Arrays.asList(thinlet.getItems(list));
		List<Integer> values = new ArrayList();
		for(Object item : items)
		{
			values.add(allItems.indexOf(item));
		}
		getViewModel().eventSelectPizzaToppings(values.toArray());
	}
	
	public void clickConfirm()
	{
		getViewModel().eventConfirm();
	}
}
