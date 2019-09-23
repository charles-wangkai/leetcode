import java.util.Arrays;

public class Solution {
	public int smallestCommonElement(int[][] mat) {
		for (int value : mat[0]) {
			if (Arrays.stream(mat).allMatch(row -> Arrays.binarySearch(row, value) >= 0)) {
				return value;
			}
		}

		return -1;
	}
}
