import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
	static private Random random = new Random();

	private List<Integer> elements = new ArrayList<Integer>();
	private Map<Integer, Integer> element2index = new HashMap<Integer, Integer>();

	/** Initialize your data structure here. */
	public RandomizedSet() {
	}

	/**
	 * Inserts a value to the set. Returns true if the set did not already contain
	 * the specified element.
	 */
	public boolean insert(int val) {
		if (element2index.containsKey(val)) {
			return false;
		} else {
			elements.add(val);
			element2index.put(val, elements.size() - 1);
			return true;
		}
	}

	/**
	 * Removes a value from the set. Returns true if the set contained the specified
	 * element.
	 */
	public boolean remove(int val) {
		if (element2index.containsKey(val)) {
			swap(val, elements.get(elements.size() - 1));
			elements.remove(elements.size() - 1);
			element2index.remove(val);
			return true;
		} else {
			return false;
		}
	}

	private void swap(int element1, int element2) {
		int index1 = element2index.get(element1);
		int index2 = element2index.get(element2);

		int temp = elements.get(index1);
		elements.set(index1, elements.get(index2));
		elements.set(index2, temp);

		element2index.put(element1, index2);
		element2index.put(element2, index1);
	}

	/** Get a random element from the set. */
	public int getRandom() {
		return elements.get(random.nextInt(elements.size()));
	}
}

// Your RandomizedSet object will be instantiated and called as such:
// RandomizedSet obj = new RandomizedSet();
// boolean param_1 = obj.insert(val);
// boolean param_2 = obj.remove(val);
// int param_3 = obj.getRandom();
