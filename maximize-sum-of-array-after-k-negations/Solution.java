import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class Solution {
	public int largestSumAfterKNegations(int[] A, int K) {
		SortedMap<Integer, Integer> numberToCount = new TreeMap<>();
		Arrays.stream(A).forEach(number -> increase(numberToCount, number));

		IntStream.range(0, K).forEach(i -> negate(numberToCount));

		return numberToCount.entrySet().stream().mapToInt(entry -> entry.getKey() * entry.getValue()).sum();
	}

	void increase(SortedMap<Integer, Integer> numberToCount, int number) {
		numberToCount.put(number, numberToCount.getOrDefault(number, 0) + 1);
	}

	void decrease(SortedMap<Integer, Integer> numberToCount, int number) {
		numberToCount.put(number, numberToCount.get(number) - 1);

		if (numberToCount.get(number) == 0) {
			numberToCount.remove(number);
		}
	}

	void negate(SortedMap<Integer, Integer> numberToCount) {
		int minNumber = numberToCount.firstKey();

		decrease(numberToCount, minNumber);
		increase(numberToCount, -minNumber);
	}
}
