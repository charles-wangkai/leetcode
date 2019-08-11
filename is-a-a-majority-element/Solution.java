import java.util.Arrays;

public class Solution {
	public boolean isMajorityElement(int[] nums, int target) {
		return Arrays.stream(nums).filter(x -> x == target).count() * 2 > nums.length;
	}
}
