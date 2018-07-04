public class Solution {
	public int[] twoSum(int[] numbers, int target) {
		int lower = 0;
		int upper = numbers.length - 1;
		int sum;
		while ((sum = numbers[lower] + numbers[upper]) != target) {
			if (sum > target) {
				upper--;
			} else {
				lower++;
			}
		}
		return new int[] { lower + 1, upper + 1 };
	}
}
