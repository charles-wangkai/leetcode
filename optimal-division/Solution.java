import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
	public String optimalDivision(int[] nums) {
		if (nums.length == 1) {
			return String.valueOf(nums[0]);
		} else if (nums.length == 2) {
			return toDivisions(nums, 0, 1);
		} else {
			return String.format("%s/(%s)", nums[0], toDivisions(nums, 1, nums.length - 1));
		}
	}

	String toDivisions(int[] nums, int beginIndex, int endIndex) {
		return String.join("/", IntStream.rangeClosed(beginIndex, endIndex).mapToObj(i -> String.valueOf(nums[i]))
				.collect(Collectors.toList()));
	}
}
