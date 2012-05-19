package viewmodels;

import java.util.List;

import models.Coin;

import rmit.mvvm.ViewModel;

public interface DeliveryViewModel extends ViewModel {
	boolean getExitView();
	void setExitView(boolean value);
	
	String getPizzaInfo();
	void setPizzaInfo(String value);
	
	int getCookTime();
	void setCookTime(int value);
	
	void eventReturn();

}
