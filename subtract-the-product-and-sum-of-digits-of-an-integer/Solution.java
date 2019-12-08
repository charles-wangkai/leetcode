import java.util.Arrays;

public class Solution {
	public int subtractProductAndSum(int n) {
		int[] digits = String.valueOf(n).chars().map(ch -> ch - '0').toArray();

		return Arrays.stream(digits).reduce((x, y) -> x * y).getAsInt() - Arrays.stream(digits).sum();
	}
}
