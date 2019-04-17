import java.util.Arrays;

public class Solution {
	public int[] sortArray(int[] nums) {
		return Arrays.stream(nums).sorted().toArray();
	}
}
