import java.util.Iterator;

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
	Iterator<Integer> iterator;
	boolean empty;
	Integer nextElement;

	public PeekingIterator(Iterator<Integer> iterator) {
		// initialize any member here.

		this.iterator = iterator;
		move();
	}

	// Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		return nextElement;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
		Integer result = nextElement;
		move();
		return result;
	}

	@Override
	public boolean hasNext() {
		return !empty;
	}

	private void move() {
		if (iterator.hasNext()) {
			empty = false;
			nextElement = iterator.next();
		} else {
			empty = true;
		}
	}
}