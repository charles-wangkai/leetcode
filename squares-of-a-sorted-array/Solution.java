import java.util.Arrays;

public class Solution {
	public int[] sortedSquares(int[] A) {
		return Arrays.stream(A).map(x -> x * x).sorted().toArray();
	}
}
