package views;

import java.io.IOException;
import java.util.*;

import models.Coin;

import rmit.mvvm.*;
import thinlet.*;
import viewmodels.*;

public class PlaceOrderView extends View {
	private Thinlet thinlet;
	private FrameLauncher frameLauncher;
	
	public PlaceOrderViewModel getViewModel()
	{
		return (PlaceOrderViewModel)super.getViewModel();
	}
	
	public PlaceOrderView() {
		thinlet = new Thinlet();
		try {
			thinlet.add(thinlet.parse("/UIDefinition/PlaceOrder.xml", this));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		frameLauncher = new FrameLauncher("Make Payment", thinlet, 320, 240);
	}
	
	@BindValue(name="ExitView")
	public void exitView()
	{
		frameLauncher.dispose();
	}

	@BindValue(name="CoinList")
	public void updateCoinList()
	{
		Object ltMoney = thinlet.find("ltMoney");
		thinlet.removeAll(ltMoney);
		Coin[] coins = getViewModel().getCoins();
		for(Coin coin : coins)
		{
			Object item = Thinlet.create("item");
			thinlet.setString(item, "text", coin.getName());
			thinlet.add(ltMoney, item);
		}
	}
	
	public void clickCancel()
	{
		getViewModel().eventCancel();
	}
}
