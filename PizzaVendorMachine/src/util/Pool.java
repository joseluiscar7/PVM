package util;

import java.util.*;

public class Pool {
	private PoolDelegate poolDelegate;
	private int initSize;
	private int maxSize;
	private boolean isClosing = false;

	private List<Object> pool;
	
	public class PoolCloseEvent
	{
		private boolean forceClose;
		private Object item;
		
		public void raiseEvent()
		{
			if (forceClose || !releaseItem(item))
				poolDelegate.destroyItem(item);
		}
	}
	
	private Object createItem()
	{
		PoolCloseEvent closeEvent = new PoolCloseEvent();
		Object item = poolDelegate.createItem(closeEvent);
		
		if(item == null) 
			return null;
		
		closeEvent.item = item;
		
		setForceClose(closeEvent);
		
		return item;
	}
	
	private synchronized void setForceClose(PoolCloseEvent event)
	{
		if (pool.size() > maxSize)
			event.forceClose = true;
	}
	
	private synchronized boolean releaseItem(Object item)
	{
		if (isClosing)
			return false;
		
		if(item != null)
			pool.add(item);
		return true;
	}
	
	private synchronized Object getItem()
	{
		int i = pool.size() - 1;
		if (i >=  0)
		{
			Object ret = pool.get(i);
			pool.remove(i);
			return ret;
		}
		return null;
	}
	
	private synchronized void setClosing()
	{
		isClosing = true;
	}
	
	public Pool(PoolDelegate poolDelegate, int initSize, int maxSize)
	{
		this.poolDelegate = poolDelegate;
		this.initSize = initSize;
		this.maxSize = maxSize;
		this.pool = new ArrayList();

		for(int i = 0; i < initSize; i++)
		{
			releaseItem(createItem());
		}
	}
	
	public Object get()
	{
		Object ret = getItem();
		
		if (ret == null)
			ret = createItem();
		
		return ret;
	}
	
	public void close()
	{
		setClosing();
		Object item;
		while((item = getItem()) != null)
			poolDelegate.destroyItem(item);
	}
}
