package business.entities.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import business.facade.Result;

/**
 * Safe iterator base class. Copies the information instead of sending objects
 * @author Michael Olson
 *
 * @param <T> The type of object we are going to iterate
 */
public abstract class SafeIterator<T> implements Iterator<Result> {
	private Iterator<T> iterator;
	private Result result = new Result();
	
	public abstract void copy(Result result, Object object);
	
    public SafeIterator(Iterator<T> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public Result next() {
        if (iterator.hasNext()) {
            copy(result, iterator.next());
        } else {
            throw new NoSuchElementException("No such element");
        }
        return result;
    }
}
