import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public boolean canReorderDoubled(int[] A) {
		List<Integer> zeros = new ArrayList<>();
		List<Integer> positives = new ArrayList<>();
		List<Integer> negatives = new ArrayList<>();
		for (int a : A) {
			if (a == 0) {
				zeros.add(a);
			} else if (a > 0) {
				positives.add(a);
			} else {
				negatives.add(a);
			}
		}

		return isPossible(zeros) && isPossible(positives) && isPossible(negatives);
	}

	boolean isPossible(List<Integer> numbers) {
		SortedMap<Integer, Integer> numberToCount = new TreeMap<>();
		for (int number : numbers) {
			int absNumber = Math.abs(number);

			numberToCount.put(absNumber, numberToCount.getOrDefault(absNumber, 0) + 1);
		}

		while (!numberToCount.isEmpty()) {
			int lower = numberToCount.firstKey();
			decrease(numberToCount, lower);

			int upper = lower * 2;
			if (!numberToCount.containsKey(upper)) {
				return false;
			}
			decrease(numberToCount, upper);
		}
		return true;
	}

	void decrease(SortedMap<Integer, Integer> numberToCount, int number) {
		numberToCount.put(number, numberToCount.get(number) - 1);

		if (numberToCount.get(number) == 0) {
			numberToCount.remove(number);
		}
	}
}
