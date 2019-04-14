import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int longestArithSeqLength(int[] A) {
		@SuppressWarnings("unchecked")
		Map<Integer, Integer>[] deltaToLengthArray = new Map[A.length];

		for (int i = 0; i < A.length; i++) {
			deltaToLengthArray[i] = new HashMap<>();

			for (int j = 0; j < i; j++) {
				int delta = A[i] - A[j];

				deltaToLengthArray[i].put(delta, Math.max(deltaToLengthArray[i].getOrDefault(delta, Integer.MIN_VALUE),
						deltaToLengthArray[j].getOrDefault(delta, 1) + 1));
			}
		}

		return Arrays.stream(deltaToLengthArray).mapToInt(
				deltaToLength -> deltaToLength.values().stream().mapToInt(x -> x).max().orElse(Integer.MIN_VALUE)).max()
				.getAsInt();
	}
}
