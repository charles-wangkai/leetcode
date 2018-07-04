public class Solution {
	public int maxSubArray(int[] A) {
		int result = Integer.MIN_VALUE;
		int sum = 0;
		for (int elem : A) {
			sum += elem;
			result = Math.max(result, sum);
			sum = Math.max(sum, 0);
		}
		return result;
	}
}
