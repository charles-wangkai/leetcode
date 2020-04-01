import java.util.Arrays;

public class Solution {
	public int singleNumber(int[] A) {
		return Arrays.stream(A).reduce((x, y) -> x ^ y).getAsInt();
	}
}
