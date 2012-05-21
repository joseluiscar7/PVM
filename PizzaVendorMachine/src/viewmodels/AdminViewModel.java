package viewmodels;

import java.util.List;

import rmit.mvvm.ViewModel;

public interface AdminViewModel extends ViewModel {
	boolean getExitView();
	void setExitView(boolean value);
	
	boolean getAuthenticateSuccess();
	void setAuthenticateSuccess(boolean value);
	
	StockInfoViewModel[] getBaseStockInfo();
	void setBaseStockInfo(StockInfoViewModel[] value);
	
	StockInfoViewModel[] getToppingStockInfo();
	void setToppingStockInfo(StockInfoViewModel[] value);
	
	void eventAuthenticate(String user, String pasword);
	void eventSetBasePrice(List<Integer> indices, float value);
	void eventSetToppingPrice(List<Integer> indices, float value);
	void eventRefillBase(List<Integer> indices);
	void eventRefillTopping(List<Integer> indices);
	
}
