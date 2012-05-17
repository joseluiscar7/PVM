package views;

import java.io.IOException;
import java.util.*;

import rmit.mvvm.*;
import thinlet.*;
import viewmodels.*;

public class InitView extends View {
	private Thinlet thinlet;
	private FrameLauncher frameLauncher;
	
	public InitViewModel getViewModel()
	{
		return (InitViewModel)super.getViewModel();
	}
	
	public InitView() {
		thinlet = new Thinlet();
		try {
			thinlet.add(thinlet.parse("/UIDefinition/InitScreen.xml", this));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		frameLauncher = new FrameLauncher("Init Screen", thinlet, 320, 240);
	}
	
	@BindValue(name="ExitView")
	public void exitView()
	{
		frameLauncher.dispose();
	}

	public void clickPVMAus()
	{
		getViewModel().eventSelectAustralia();
	}
	
	public void clickPVMChina()
	{
		getViewModel().eventSelectChina();
	}
	
	public void clickPVMIndia()
	{
		getViewModel().eventSelectIndia();
	}
	
	public void clickService()
	{
		getViewModel().eventService();
	}
}
