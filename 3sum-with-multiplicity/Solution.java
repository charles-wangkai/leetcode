import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	static final int MOD_DIVISOR = 1_000_000_007;

	public int threeSumMulti(int[] A, int target) {
		SortedMap<Integer, Integer> numberToCount = buildNumberToCount(A);

		int result = 0;
		for (int a : numberToCount.keySet()) {
			for (int b : numberToCount.keySet()) {
				if (b < a) {
					continue;
				}

				int c = target - a - b;
				if (c >= b && numberToCount.containsKey(c)) {
					result = addMod(result, computeWays(numberToCount, a, b, c));
				}
			}
		}
		return result;
	}

	int computeWays(SortedMap<Integer, Integer> numberToCount, int a, int b, int c) {
		int countA = numberToCount.get(a);
		int countB = numberToCount.get(b);
		int countC = numberToCount.get(c);

		if (a != b && b != c) {
			return multiplyMod(multiplyMod(CMod(countA, 1), CMod(countB, 1)), CMod(countC, 1));
		} else if (a == b && b != c) {
			return multiplyMod(CMod(countA, 2), CMod(countC, 1));
		} else if (a != b && b == c) {
			return multiplyMod(CMod(countA, 1), CMod(countC, 2));
		} else {
			return CMod(countA, 3);
		}
	}

	int addMod(int x, int y) {
		return (x + y) % MOD_DIVISOR;
	}

	int multiplyMod(int x, int y) {
		return (int) ((long) x * y % MOD_DIVISOR);
	}

	int CMod(int n, int r) {
		long result = 1;
		for (int i = 1; i <= r; i++) {
			result = result * (n - i + 1) / i;
		}
		return (int) (result % MOD_DIVISOR);
	}

	SortedMap<Integer, Integer> buildNumberToCount(int[] A) {
		SortedMap<Integer, Integer> numberToCount = new TreeMap<>();
		for (int number : A) {
			numberToCount.put(number, numberToCount.getOrDefault(number, 0) + 1);
		}
		return numberToCount;
	}
}
