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
	
	@BindValue(name="OwingFee")
	public void updateOwingFee()
	{
		Object lbDueAmount = thinlet.find("lbDueAmount");
		thinlet.setString(lbDueAmount, "text", "Outstanding payment: " + getViewModel().getOwingFee());
	}
	
	
	@BindValue(name="CoinList")
	public void updateCoinList()
	{
		Object ltMoney = thinlet.find("ltMoney");
		thinlet.removeAll(ltMoney);
		List<String> coins = getViewModel().getCoinList();
		for(String coin : coins)
		{
			Object item = Thinlet.create("item");
			thinlet.setString(item, "text", coin);
			thinlet.add(ltMoney, item);
		}
	}
	
	public void payMoney(Object list)
	{
		int itemIndex = thinlet.getSelectedIndex(list);
		Object item = thinlet.getItem(list, itemIndex);
		thinlet.setBoolean(item, "selected", false);
		getViewModel().eventSelectCoin(itemIndex);
	}
	
	public void clickCancel()
	{
		getViewModel().eventCancel();
	}
}
