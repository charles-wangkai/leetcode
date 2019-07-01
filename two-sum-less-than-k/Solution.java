import java.util.stream.IntStream;

public class Solution {
	public int twoSumLessThanK(int[] A, int K) {
		return IntStream.range(0, A.length).flatMap(i -> IntStream.range(i + 1, A.length).map(j -> A[i] + A[j]))
				.filter(x -> x < K).max().orElse(-1);
	}
}
