import java.util.Arrays;

public class FindPivotIndex {
	public int pivotIndex(int[] nums) {
		int leftSum = 0;
		int rightSum = Arrays.stream(nums).sum();
		for (int i = 0; i < nums.length; i++) {
			if (i > 0) {
				leftSum += nums[i - 1];
			}
			rightSum -= nums[i];

			if (leftSum == rightSum) {
				return i;
			}
		}
		return -1;
	}
}
