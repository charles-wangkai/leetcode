import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int subarraysDivByK(int[] A, int K) {
		int totalRemainder = Arrays.stream(A).reduce(0, (result, element) -> addMod(result, element, K));

		Map<Integer, Integer> rightRemainderToCount = new HashMap<>();
		int rightRemainder = 0;
		for (int i = A.length; i >= 1; i--) {
			rightRemainder = addMod(rightRemainder, (i == A.length) ? 0 : A[i], K);

			rightRemainderToCount.put(rightRemainder, rightRemainderToCount.getOrDefault(rightRemainder, 0) + 1);
		}

		int result = 0;
		int leftRemainder = 0;
		for (int i = -1; i <= A.length - 2; i++) {
			leftRemainder = addMod(leftRemainder, (i == -1) ? 0 : A[i], K);

			result += rightRemainderToCount.getOrDefault(addMod(totalRemainder, -leftRemainder, K), 0);

			rightRemainderToCount.put(rightRemainder, rightRemainderToCount.get(rightRemainder) - 1);
			rightRemainder = addMod(rightRemainder, -((i + 2 == A.length) ? 0 : A[i + 2]), K);
		}
		return result;
	}

	int addMod(int x, int y, int m) {
		return ((x + y) % m + m) % m;
	}
}
