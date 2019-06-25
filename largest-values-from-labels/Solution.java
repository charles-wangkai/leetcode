import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
		Item[] items = new Item[values.length];
		for (int i = 0; i < items.length; i++) {
			items[i] = new Item(values[i], labels[i]);
		}

		Arrays.sort(items, (item1, item2) -> Integer.compare(item2.value, item1.value));

		int totalCount = 0;
		Map<Integer, Integer> labelToCount = new HashMap<>();
		int result = 0;
		for (Item item : items) {
			if (totalCount < num_wanted && labelToCount.getOrDefault(item.label, 0) < use_limit) {
				totalCount++;
				labelToCount.put(item.label, labelToCount.getOrDefault(item.label, 0) + 1);
				result += item.value;
			}
		}
		return result;
	}
}

class Item {
	int value;
	int label;

	Item(int value, int label) {
		this.value = value;
		this.label = label;
	}
}