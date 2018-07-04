import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
	public boolean canPartition(int[] nums) {
		int sum = Arrays.stream(nums).sum();

		if (sum % 2 != 0) {
			return false;
		}

		int target = sum / 2;
		Set<Integer> partialSums = new HashSet<Integer>();
		partialSums.add(0);

		for (int num : nums) {
			Set<Integer> nextPartialSums = new HashSet<Integer>();
			nextPartialSums.addAll(partialSums);

			for (int partialSum : partialSums) {
				int nextPartialSum = partialSum + num;

				if (nextPartialSum == target) {
					return true;
				} else if (nextPartialSum < target) {
					nextPartialSums.add(nextPartialSum);
				}
			}

			partialSums = nextPartialSums;
		}

		return false;
	}
}
