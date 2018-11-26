import java.util.Arrays;

public class Solution {
	public int minIncrementForUnique(int[] A) {
		Arrays.sort(A);

		int result = 0;
		int lower = 0;
		for (int a : A) {
			lower = Math.max(lower, a);
			result += lower - a;
			lower++;
		}
		return result;
	}
}
