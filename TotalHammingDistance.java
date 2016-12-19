import java.util.Arrays;

public class TotalHammingDistance {
	public int totalHammingDistance(int[] nums) {
		int result = 0;
		while (!isAllEqual(nums)) {
			int[] counts = new int[2];

			for (int i = 0; i < nums.length; i++) {
				counts[nums[i] & 1]++;
				nums[i] >>= 1;
			}
			result += counts[0] * counts[1];
		}
		return result;
	}

	boolean isAllEqual(int[] nums) {
		return Arrays.stream(nums).allMatch(num -> num == nums[0]);
	}
}
