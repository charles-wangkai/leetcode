import java.util.Arrays;

public class Solution {
	public boolean isGoodArray(int[] nums) {
		return Arrays.stream(nums).reduce(this::gcd).getAsInt() == 1;
	}

	int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}
}
