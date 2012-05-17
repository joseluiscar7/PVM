package presenters;

import java.util.*;

import models.*;
import rmit.mvvm.*;
import states.*;
import viewmodels.*;

public class InitPresenter extends Presenter {
	private InitState state;
	
	public InitPresenter(InitState state) {
		this.state = state;
	}

	public InitViewModel getViewModel()
	{
		return (InitViewModel)super.getViewModel();
	}
	
	public void load()
	{
	}
	
	private void selectVendor(int vendorID)
	{
		getViewModel().setExitView(true);
		state.getStateContext().setVendorID(vendorID);
		state.stop();
	}
	
	@BindEvent(name="SelectAustralia")
	public void onSelectAustralia(Object[] args)
	{
		selectVendor(1);
	}
	
	@BindEvent(name="SelectChina")
	public void onSelectChina(Object[] args)
	{
		selectVendor(2);
	}
	
	@BindEvent(name="SelectIndia")
	public void onSelectIndia(Object[] args)
	{
		selectVendor(3);
	}
	
	@BindEvent(name="Service")
	public void onService(Object[] args)
	{
		getViewModel().setExitView(true);
		state.stop();
	}
}
