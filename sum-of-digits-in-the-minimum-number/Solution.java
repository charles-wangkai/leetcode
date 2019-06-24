import java.util.Arrays;

public class Solution {
	public int sumOfDigits(int[] A) {
		return 1 - String.valueOf(Arrays.stream(A).min().getAsInt()).chars().map(ch -> ch - '0').sum() % 2;
	}
}
