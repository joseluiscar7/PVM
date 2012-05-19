package viewmodels;

import java.util.List;

import models.Coin;

import rmit.mvvm.ViewModel;

public interface PlaceOrderViewModel extends ViewModel {
	boolean getExitView();
	void setExitView(boolean value);

	List<String> getCoinList();
	void setCoinList(List<String> value);
	
	float getOwingFee();
	void setOwingFee(float value);
	
	void eventSelectCoin(int idx);
	void eventCancel();
}
