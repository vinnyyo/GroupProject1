package business.entities.iterators;

import java.util.Iterator;

import business.entities.Order;
import business.facade.Result;

/**
 * Safe iterator for Order lists
 * 
 * @author Vincent Peterson
 *
 */
public class SafeOrderIterator extends SafeIterator<Order> {

	public SafeOrderIterator(Iterator<Order> iterator) {
		super(iterator);
	}

	@Override
	public void copy(Result result, Object object) {
		Order order = (Order) object;
		result.setOrder(order);
	}
}
