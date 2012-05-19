package viewmodels;

import java.util.List;

import rmit.mvvm.ViewModel;

public interface InitViewModel extends ViewModel {
	boolean getExitView();
	void setExitView(boolean value);
	
	void eventSelect(int idx);
	void eventService(int idx);
}
