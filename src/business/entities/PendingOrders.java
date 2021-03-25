package business.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class PendingOrders implements Serializable, Iterable<Order> {

	private static final long serialVersionUID = 1L;
	private List<Order> orderItems;

	public void addOrderItem(Order order) {
		orderItems.add(order);
	}

	public Order searchOrderId(int orderId) {
		Iterator<Order> orderIterator = orderItems.iterator();
		while (orderIterator.hasNext()) {
			Order cursor = orderIterator.next();
			if (cursor.matchesId(orderId)) {
				return cursor;
			}
		}
		return null;
	}

	public Order deleteOrderItem(int orderId) {
		Order item = this.searchOrderId(orderId);
		if (item == null) {
			return null;
		}
		orderItems.remove(item);
		return item;
	}

	@Override
	public Iterator<Order> iterator() {
		return orderItems.iterator();
	}

	public String toString() {
		return orderItems.toString();
	}
}
