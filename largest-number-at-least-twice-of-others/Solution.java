import java.util.Arrays;
import java.util.stream.IntStream;

public class Solution {
	public int dominantIndex(int[] nums) {
		int largest = Arrays.stream(nums).max().getAsInt();
		if (Arrays.stream(nums).allMatch(num -> num == largest || num * 2 <= largest)) {
			return IntStream.range(0, nums.length).filter(i -> nums[i] == largest).toArray()[0];
		} else {
			return -1;
		}
	}
}
