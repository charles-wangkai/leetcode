import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Solution {
	public boolean splitArray(int[] nums) {
		for (int j = 3; j <= nums.length - 4; j++) {
			Set<Integer> leftSums = searchSums(nums, 0, j - 1);
			Set<Integer> rightSums = searchSums(nums, j + 1, nums.length - 1);

			if (isIntersect(leftSums, rightSums)) {
				return true;
			}
		}
		return false;
	}

	Set<Integer> searchSums(int[] nums, int beginIndex, int endIndex) {
		int total = IntStream.rangeClosed(beginIndex, endIndex).map(i -> nums[i]).sum();

		int leftSum = 0;
		Set<Integer> sums = new HashSet<Integer>();
		for (int i = beginIndex; i + 1 < endIndex; i++) {
			leftSum += nums[i];

			int rightSum = total - leftSum - nums[i + 1];
			if (leftSum == rightSum) {
				sums.add(leftSum);
			}
		}
		return sums;
	}

	boolean isIntersect(Set<Integer> s1, Set<Integer> s2) {
		for (int n1 : s1) {
			if (s2.contains(n1)) {
				return true;
			}
		}
		return false;
	}
}
