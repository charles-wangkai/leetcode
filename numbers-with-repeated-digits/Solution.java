import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
	public int numDupDigitsAtMostN(int N) {
		int[] L = String.valueOf(N + 1).chars().map(ch -> ch - '0').toArray();

		int noRepeatNum = 0;

		for (int length = 1; length < L.length; length++) {
			noRepeatNum += 9 * P(9, length - 1);
		}

		Set<Integer> seen = new HashSet<>();
		for (int i = 0; i < L.length; i++) {
			for (int digit = (i == 0) ? 1 : 0; digit < L[i]; digit++) {
				if (!seen.contains(digit)) {
					noRepeatNum += P(9 - i, L.length - i - 1);
				}
			}

			if (seen.contains(L[i])) {
				break;
			}

			seen.add(L[i]);
		}

		return N - noRepeatNum;
	}

	int P(int n, int m) {
		return IntStream.range(0, m).reduce(1, (result, element) -> result * (n - element));
	}
}
