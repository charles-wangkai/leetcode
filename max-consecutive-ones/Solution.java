public class Solution {
	public int findMaxConsecutiveOnes(int[] nums) {
		int maxOneNum = 0;
		int oneNum = 0;
		for (int num : nums) {
			if (num == 0) {
				oneNum = 0;
			} else {
				oneNum++;
				maxOneNum = Math.max(maxOneNum, oneNum);
			}
		}
		return maxOneNum;
	}
}
