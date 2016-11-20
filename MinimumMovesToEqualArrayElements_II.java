import java.util.Arrays;

public class MinimumMovesToEqualArrayElements_II {
	public int minMoves2(int[] nums) {
		Arrays.sort(nums);

		int moveNum = 0;
		for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
			moveNum += nums[j] - nums[i];
		}
		return moveNum;
	}
}
