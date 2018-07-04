public class Solution {
	public int singleNumber(int[] A) {
		int result = 0;
		for (int number : A) {
			result ^= number;
		}
		return result;
	}
}
