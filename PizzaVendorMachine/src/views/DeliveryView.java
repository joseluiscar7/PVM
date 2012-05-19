package views;

import java.awt.Font;
import java.io.IOException;
import java.util.*;

import rmit.mvvm.*;
import thinlet.*;
import viewmodels.*;

public class DeliveryView extends View {
	private Thinlet thinlet;
	private FrameLauncher frameLauncher;
	
	public DeliveryViewModel getViewModel()
	{
		return (DeliveryViewModel)super.getViewModel();
	}
	
	public DeliveryView() {
		thinlet = new Thinlet();
		try {
			thinlet.add(thinlet.parse("/UIDefinition/Delivery.xml", this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		frameLauncher = new FrameLauncher("Delivery Screen", thinlet, 320, 240);
		
		final int totalCookTime = getViewModel().getCookTime();
		final Object lbCookTime = thinlet.find("lbCookTime");
		thinlet.setFont(lbCookTime, new Font("Arial", Font.BOLD, 64));
		new Thread(new Runnable() {
			@Override
			public void run() {
				int cookTime = totalCookTime;
				for(; cookTime > 0; cookTime-- )
				{
					thinlet.setString(lbCookTime, "text", String.format("%d", cookTime));
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {}
				}
				thinlet.setBoolean(thinlet.find("pnlCooking"), "visible", false);
				thinlet.setBoolean(thinlet.find("pnlCooked"), "visible", true);
			}
		}).start();
	}
	
	@BindValue(name="ExitView")
	public void exitView()
	{
		frameLauncher.dispose();
	}

	@BindValue(name="PizzaInfo")
	public void setPizzaInfo()
	{
		Object lbInfo = thinlet.find("info");
		thinlet.setString(lbInfo, "text", getViewModel().getPizzaInfo());
	}
	
	public void clickReturn()
	{
		getViewModel().eventReturn();
	}
}
