public class MaxConsecutiveOnes {
	public int findMaxConsecutiveOnes(int[] nums) {
		int maxOneNum = 0;
		int oneNum = 0;
		for (int i = 0; i <= nums.length; i++) {
			if (i < nums.length && nums[i] == 1) {
				oneNum++;
			} else {
				maxOneNum = Math.max(maxOneNum, oneNum);
				oneNum = 0;
			}
		}
		return maxOneNum;
	}
}
