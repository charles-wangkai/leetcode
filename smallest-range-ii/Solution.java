import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public int smallestRangeII(int[] A, int K) {
		Arrays.sort(A);

		SortedMap<Integer, Integer> numberToCount = new TreeMap<>();
		for (int number : A) {
			addNumber(numberToCount, number);
		}
		int minRange = computeRange(numberToCount);

		for (int number : A) {
			removeNumber(numberToCount, number);
			addNumber(numberToCount, number + 2 * K);

			minRange = Math.min(minRange, computeRange(numberToCount));
		}

		return minRange;
	}

	void addNumber(SortedMap<Integer, Integer> numberToCount, int number) {
		numberToCount.put(number, numberToCount.getOrDefault(number, 0) + 1);
	}

	void removeNumber(SortedMap<Integer, Integer> numberToCount, int number) {
		numberToCount.put(number, numberToCount.get(number) - 1);

		if (numberToCount.get(number) == 0) {
			numberToCount.remove(number);
		}
	}

	int computeRange(SortedMap<Integer, Integer> numberToCount) {
		return numberToCount.lastKey() - numberToCount.firstKey();
	}
}
