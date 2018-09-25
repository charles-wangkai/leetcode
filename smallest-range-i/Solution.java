import java.util.Arrays;

public class Solution {
	public int smallestRangeI(int[] A, int K) {
		return Math.max(0, Arrays.stream(A).max().getAsInt() - Arrays.stream(A).min().getAsInt() - K * 2);
	}
}
