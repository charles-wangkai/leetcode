import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ZigzagIterator {
	List<Iterator<Integer>> iterators;
	Iterator<Iterator<Integer>> iter;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		iterators = new LinkedList<Iterator<Integer>>();

		if (!v1.isEmpty()) {
			iterators.add(v1.iterator());
		}
		if (!v2.isEmpty()) {
			iterators.add(v2.iterator());
		}

		iter = iterators.iterator();
	}

	public int next() {
		if (!iter.hasNext()) {
			iter = iterators.iterator();
		}

		Iterator<Integer> current = iter.next();
		int result = current.next();

		if (!current.hasNext()) {
			iter.remove();
		}

		return result;
	}

	public boolean hasNext() {
		return !iterators.isEmpty();
	}
}

// Your ZigzagIterator object will be instantiated and called as such:
// ZigzagIterator i = new ZigzagIterator(v1, v2);
// while (i.hasNext()) v[f()] = i.next();
