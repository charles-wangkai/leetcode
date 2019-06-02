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

	public boolean confusingNumber(int N) {
		Integer rotated = rotate(N);

		return rotated != null && rotated != N;
	}

	Integer rotate(int N) {
		int result = 0;
		while (N != 0) {
			int digit = N % 10;

			if (!DIGIT_TO_ROTATED.containsKey(digit)) {
				return null;
			}

			result = result * 10 + DIGIT_TO_ROTATED.get(digit);
			N /= 10;
		}
		return result;
	}
}
