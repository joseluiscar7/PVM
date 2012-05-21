package views;

import java.io.IOException;
import java.util.*;

import javax.swing.JOptionPane;

import rmit.mvvm.*;
import thinlet.*;
import viewmodels.*;

public class AdminView extends View {
	private Thinlet thinlet;
	private FrameLauncher frameLauncher;
	private List<Integer> selectedBaseIndex;
	private List<Integer> selectedToppingIndex;
	
	public AdminViewModel getViewModel()
	{
		return (AdminViewModel)super.getViewModel();
	}
	
	public AdminView() {
		thinlet = new Thinlet();
		try {
			thinlet.add(thinlet.parse("/UIDefinition/Admin.xml", this));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load() {
		frameLauncher = new FrameLauncher("Admin", thinlet, 320, 240);
	}
	
	@BindValue(name="ExitView")
	public void exitView()
	{
		frameLauncher.dispose();
	}

	public void login(String username, String password)
	{
		getViewModel().eventAuthenticate(username, password);
	}
	
	public void itemSelected(Object table, int tableId, Object label)
	{
		Object[] items = thinlet.getSelectedItems(table);
		List allItems = Arrays.asList(thinlet.getItems(table));
		List<Integer> values = new ArrayList();
		for(Object item : items)
		{
			values.add(allItems.indexOf(item));				
		}
		thinlet.setString(label, "text", values.size() > 1 ? "Set discount" : "Set price"); 
		if (tableId == 1) 
			selectedBaseIndex = values;
		else
			selectedToppingIndex = values;
	}
	
	public void clickSet(int tableId, String strValue)
	{
		float value;
		try
		{
			value = Float.parseFloat(strValue);
		}
		catch(Exception e)
		{
			showMessage("Wrong input format");
			return;
		}
		if (tableId == 1)
			getViewModel().eventSetBasePrice(selectedBaseIndex, value);
		else
			getViewModel().eventSetToppingPrice(selectedToppingIndex, value);
	}
	
	public void clickRefill(int tableId)
	{
		if (tableId == 1)
			getViewModel().eventRefillBase(selectedBaseIndex);
		else
			getViewModel().eventRefillTopping(selectedToppingIndex);
	}
	
	@BindValue(name="AuthenticateSuccess")
	public void updateAuthenticateSuccess()
	{
		if (getViewModel().getAuthenticateSuccess())
		{
			thinlet.setBoolean(thinlet.find("pnlLogin"), "visible", false);
			thinlet.setBoolean(thinlet.find("pnlViewStock"), "visible", true);
		}
		else
		{
			showMessage("Wrong username or password");
		}
	}
	
	private void showMessage(String msg)
	{
		JOptionPane.showConfirmDialog(null, msg, "alert", JOptionPane.DEFAULT_OPTION);
	}
	
	private void updateStockInfo(String elementName, StockInfoViewModel[] items)
	{
		Object tblElement = thinlet.find(elementName);
		thinlet.removeAll(tblElement);
		for(StockInfoViewModel item : items)
		{
			Object row = thinlet.create("row");
			
			Object cName = thinlet.create("cell");
			Object cPrice = thinlet.create("cell");
			Object cStock = thinlet.create("cell");
			
			thinlet.setString(cName, "text", item.getName());
			thinlet.setString(cPrice, "text", ((Float)item.getPrice()).toString());
			thinlet.setString(cStock, "text", ((Integer)item.getCount()).toString());
			
			thinlet.add(row, cName);
			thinlet.add(row, cPrice);
			thinlet.add(row, cStock);
			
			thinlet.add(tblElement, row);
		}
	}
	
	@BindValue(name="BaseStockInfo")
	public void updateBaseStockInfo()
	{
		updateStockInfo("tblBase", getViewModel().getBaseStockInfo());
	}
	
	@BindValue(name="ToppingStockInfo")
	public void updateToppingStockInfo()
	{
		updateStockInfo("tblTopping", getViewModel().getToppingStockInfo());
	}
}
