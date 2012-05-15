package viewmodels;

import java.util.List;

import rmit.mvvm.ViewModel;

public interface SelectPizzaViewModel extends ViewModel {
	List<String> getPizzaBaseList();
	void setPizzaBaseList(List<String> value);
	
	List<String> getPizzaToppingList();
	void setPizzaToppingList(List<String> value);
	
	boolean getExitView();
	void setExitView(boolean value);
	
	void eventSelectPizzaBase(String name);
	void eventSelectPizzaToppings(String[] name);
	void eventConfirm();
}