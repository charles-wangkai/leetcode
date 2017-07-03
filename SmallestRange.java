import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class SmallestRange {
	public int[] smallestRange(List<List<Integer>> nums) {
		int lower = -100001;
		int upper = 100001;

		@SuppressWarnings("unchecked")
		Iterator<Integer>[] iterators = new Iterator[nums.size()];
		for (int i = 0; i < iterators.length; i++) {
			iterators[i] = nums.get(i).iterator();
		}

		TreeSet<IndexAndValue> ivs = new TreeSet<IndexAndValue>(
				(iv1, iv2) -> (iv1.value != iv2.value) ? (iv1.value - iv2.value) : (iv1.index - iv2.index));
		for (int i = 0; i < nums.size(); i++) {
			ivs.add(new IndexAndValue(i, iterators[i].next()));
		}

		while (true) {
			int currentLower = ivs.first().value;
			int currentUpper = ivs.last().value;
			if (currentUpper - currentLower < upper - lower
					|| (currentUpper - currentLower == upper - lower && currentLower < lower)) {
				lower = currentLower;
				upper = currentUpper;
			}

			IndexAndValue min = ivs.pollFirst();

			if (!iterators[min.index].hasNext()) {
				break;
			}

			int value = iterators[min.index].next();
			ivs.add(new IndexAndValue(min.index, value));
		}

		return new int[] { lower, upper };
	}
}

class IndexAndValue {
	int index;
	int value;

	IndexAndValue(int index, int value) {
		this.index = index;
		this.value = value;
	}
}