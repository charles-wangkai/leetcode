import java.util.stream.IntStream;

public class Solution {
	public boolean isMonotonic(int[] A) {
		return IntStream.range(0, A.length - 1).allMatch(i -> A[i] <= A[i + 1])
				|| IntStream.range(0, A.length - 1).allMatch(i -> A[i] >= A[i + 1]);
	}
}
