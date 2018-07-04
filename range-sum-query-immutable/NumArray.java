public class NumArray {
	int[] accumulativeSums;

	public NumArray(int[] nums) {
		accumulativeSums = new int[nums.length];
		int accumulativeSum = 0;
		for (int i = 0; i < accumulativeSums.length; i++) {
			accumulativeSum += nums[i];
			accumulativeSums[i] = accumulativeSum;
		}
	}

	public int sumRange(int i, int j) {
		return accumulativeSums[j] - (i == 0 ? 0 : accumulativeSums[i - 1]);
	}
}

// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);