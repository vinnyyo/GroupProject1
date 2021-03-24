package business.entities;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

public class PendingOrders implements Serializable, Iterable<OrderItem> {

	private static final long serialVersionUID = 1L;
	private List<OrderItem> orderItems;

	public void addOrderItem(OrderItem order) {
		orderItems.add(order);
	}

	public OrderItem searchOrderId(int orderId) {
		Iterator<OrderItem> orderIterator = orderItems.iterator();
		while (orderIterator.hasNext()) {
			OrderItem cursor = orderIterator.next();
			if (cursor.matchesId(orderId)) {
				return cursor;
			}
		}
		return null;
	}

	public OrderItem deleteOrderItem(int orderId) {
		OrderItem item = this.searchOrderId(orderId);
		if (item == null) {
			return null;
		}
		orderItems.remove(item);
		return item;
	}

	@Override
	public Iterator<OrderItem> iterator() {
		return orderItems.iterator();
	}

	public toString() {
		
	}
}
