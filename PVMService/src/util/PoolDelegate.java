package util;

import util.Pool.PoolCloseEvent;

public interface PoolDelegate {
	Object createItem(PoolCloseEvent event);
	void destroyItem(Object item);
}
