import java.util.stream.IntStream;

public class Solution {
	public int maxScoreSightseeingPair(int[] A) {
		int[] normalized = IntStream.range(0, A.length).map(i -> A[i] - i).toArray();

		int[] afterMaxs = new int[normalized.length - 1];
		int afterMax = Integer.MIN_VALUE;
		for (int i = afterMaxs.length - 1; i >= 0; i--) {
			afterMax = Math.max(afterMax, normalized[i + 1]);
			afterMaxs[i] = afterMax;
		}

		return IntStream.range(0, afterMaxs.length).map(i -> normalized[i] + afterMaxs[i] + i * 2).max().getAsInt();
	}
}
