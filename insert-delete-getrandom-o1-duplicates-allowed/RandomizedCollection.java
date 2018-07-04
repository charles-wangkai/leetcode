import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomizedCollection {
	static private Random random = new Random();

	private List<Integer> elements = new ArrayList<Integer>();
	private Map<Integer, Set<Integer>> element2indices = new HashMap<Integer, Set<Integer>>();

	/** Initialize your data structure here. */
	public RandomizedCollection() {
	}

	/**
	 * Inserts a value to the collection. Returns true if the collection did not
	 * already contain the specified element.
	 */
	public boolean insert(int val) {
		boolean result;

		if (element2indices.containsKey(val)) {
			result = false;
		} else {
			element2indices.put(val, new LinkedHashSet<Integer>());
			result = true;
		}

		elements.add(val);
		element2indices.get(val).add(elements.size() - 1);

		return result;
	}

	/**
	 * Removes a value from the collection. Returns true if the collection
	 * contained the specified element.
	 */
	public boolean remove(int val) {
		if (element2indices.containsKey(val)) {
			swap(getAnyIndex(val), elements.size() - 1);

			Set<Integer> valIndicies = element2indices.get(val);
			valIndicies.remove(elements.size() - 1);
			if (valIndicies.isEmpty()) {
				element2indices.remove(val);
			}

			elements.remove(elements.size() - 1);

			return true;
		} else {
			return false;
		}
	}

	private int getAnyIndex(int val) {
		return element2indices.get(val).iterator().next();
	}

	private void swap(int index1, int index2) {
		int element1 = elements.get(index1);
		int element2 = elements.get(index2);

		int temp = elements.get(index1);
		elements.set(index1, elements.get(index2));
		elements.set(index2, temp);

		Set<Integer> indices1 = element2indices.get(element1);
		indices1.remove(index1);
		indices1.add(index2);

		Set<Integer> indices2 = element2indices.get(element2);
		indices2.remove(index2);
		indices2.add(index1);
	}

	/** Get a random element from the collection. */
	public int getRandom() {
		return elements.get(random.nextInt(elements.size()));
	}
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection(); boolean param_1 =
 * obj.insert(val); boolean param_2 = obj.remove(val); int param_3 =
 * obj.getRandom();
 */