import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean checkSubarraySum(int[] nums, int k) {
		if (k == 0) {
			return hasTwoContinuousZeros(nums);
		}

		Set<Integer> remainders = new HashSet<Integer>();

		int[] runningSums = new int[nums.length];
		int runningSum = 0;
		for (int i = 0; i < runningSums.length; i++) {
			runningSum = addMod(runningSum, nums[i], k);
			runningSums[i] = runningSum;

			if (i == 1) {
				remainders.add(0);
			} else if (i >= 2) {
				remainders.add(runningSums[i - 2]);
			}

			if (remainders.contains(runningSums[i])) {
				return true;
			}
		}

		return false;
	}

	int addMod(int x, int y, int modulus) {
		return (x + y) % modulus;
	}

	boolean hasTwoContinuousZeros(int[] nums) {
		for (int i = 0; i < nums.length - 1; i++) {
			if (nums[i] == 0 && nums[i + 1] == 0) {
				return true;
			}
		}
		return false;
	}
}
