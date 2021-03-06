package business.entities.iterators;

import java.util.Iterator;
import business.entities.Product;
import business.facade.Result;

/**
 * Safe iterator for Product lists
 * @author Michael Olson
 *
 */
public class SafeProductIterator extends SafeIterator<Product> {

	public SafeProductIterator(Iterator<Product> iterator) {
		super(iterator);
	}

	@Override
	public void copy(Result result, Object object) {
        Product product = (Product) object;
        result.setProduct(product);
    }
}
