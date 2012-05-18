package viewmodels;

import java.util.List;

import models.Coin;

import rmit.mvvm.ViewModel;

public interface PlaceOrderViewModel extends ViewModel {
	boolean getExitView();
	void setExitView(boolean value);

	Coin[] getCoins();
	void setCoins(Coin[] value);
	
	void eventCancel();
}
