public class Solution {
	public int[] findErrorNums(int[] nums) {
		int[] counts = new int[nums.length + 1];
		for (int num : nums) {
			counts[num]++;
		}

		int[] result = new int[2];
		for (int i = 1; i < counts.length; i++) {
			if (counts[i] == 2) {
				result[0] = i;
			} else if (counts[i] == 0) {
				result[1] = i;
			}
		}
		return result;
	}
}
