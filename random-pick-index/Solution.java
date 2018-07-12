import java.util.Random;

public class Solution {
	private Random random = new Random();
	private int[] nums;

	public Solution(int[] nums) {
		this.nums = nums;
	}

	public int pick(int target) {
		int chosenIndex = -1;
		int size = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != target) {
				continue;
			}

			size++;
			if (random.nextInt(size) == 0) {
				chosenIndex = i;
			}
		}
		return chosenIndex;
	}
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(nums);
// int param_1 = obj.pick(target);
