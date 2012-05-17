package viewmodels;

import java.util.List;

import rmit.mvvm.ViewModel;

public interface InitViewModel extends ViewModel {
	boolean getExitView();
	void setExitView(boolean value);
	
	void eventSelectAustralia();
	void eventSelectChina();
	void eventSelectIndia();
	void eventService();
}
