import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Solution {
	public int longestOnes(int[] A, int K) {
		List<Integer> zeroIndices = new ArrayList<>();
		zeroIndices.add(-1);
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				zeroIndices.add(i);
			}
		}
		zeroIndices.add(A.length);

		if (zeroIndices.size() - 2 < K) {
			return A.length;
		}

		return IntStream.range(0, zeroIndices.size() - K - 1)
				.map(i -> zeroIndices.get(i + K + 1) - zeroIndices.get(i) - 1).max().getAsInt();
	}
}
