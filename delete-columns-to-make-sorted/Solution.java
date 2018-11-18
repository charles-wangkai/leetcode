import java.util.stream.IntStream;

public class Solution {
	public int minDeletionSize(String[] A) {
		return (int) IntStream.range(0, A[0].length()).filter(c -> !isNonDecreasing(A, c)).count();
	}

	static boolean isNonDecreasing(String[] A, int c) {
		return IntStream.range(0, A.length - 1).allMatch(i -> A[i].charAt(c) <= A[i + 1].charAt(c));
	}
}
