import java.util.stream.IntStream;

public class Solution {
	public boolean isIdealPermutation(int[] A) {
		return IntStream.range(0, A.length).allMatch(i -> Math.abs(A[i] - i) <= 1);
	}
}
