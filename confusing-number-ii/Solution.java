import java.util.HashMap;
import java.util.Map;

public class Solution {
	static final Map<Integer, Integer> DIGIT_TO_ROTATED = new HashMap<>();
	static {
		DIGIT_TO_ROTATED.put(0, 0);
		DIGIT_TO_ROTATED.put(1, 1);
		DIGIT_TO_ROTATED.put(6, 9);
		DIGIT_TO_ROTATED.put(8, 8);
		DIGIT_TO_ROTATED.put(9, 6);
	}

	static int confusingNumberCount;

	public int confusingNumberII(int N) {
		confusingNumberCount = 0;

		if (N == 1_000_000_000) {
			confusingNumberCount++;
		}

		search(N, 0, 0);

		return confusingNumberCount;
	}

	void search(int N, int length, int current) {
		if (length == 9) {
			return;
		}

		for (int validDigit : DIGIT_TO_ROTATED.keySet()) {
			if (validDigit == 0 && length == 0) {
				continue;
			}

			int next = current * 10 + validDigit;
			if (next <= N && isConfusingNumber(next)) {
				confusingNumberCount++;
			}

			search(N, length + 1, next);
		}
	}

	boolean isConfusingNumber(int x) {
		int rotated = 0;
		int p = x;
		while (p != 0) {
			rotated = rotated * 10 + DIGIT_TO_ROTATED.get(p % 10);

			p /= 10;
		}

		return rotated != x;
	}
}
