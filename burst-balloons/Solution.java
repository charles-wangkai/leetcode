public class Solution {
	public int maxCoins(int[] nums) {
		int[] numbers = new int[nums.length + 2];
		numbers[0] = 1;
		for (int i = 1; i < numbers.length - 1; i++) {
			numbers[i] = nums[i - 1];
		}
		numbers[numbers.length - 1] = 1;

		int[][] results = new int[numbers.length - 1][numbers.length];
		for (int length = 3; length <= numbers.length; length++) {
			for (int beginIndex = 0, endIndex = beginIndex + length
					- 1; endIndex < numbers.length; beginIndex++, endIndex++) {
				for (int middleIndex = beginIndex + 1; middleIndex < endIndex; middleIndex++) {
					results[beginIndex][endIndex] = Math.max(results[beginIndex][endIndex],
							results[beginIndex][middleIndex] + results[middleIndex][endIndex]
									+ numbers[beginIndex] * numbers[middleIndex] * numbers[endIndex]);
				}
			}
		}
		return results[0][numbers.length - 1];
	}
}
