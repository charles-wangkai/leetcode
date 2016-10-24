import java.util.HashSet;
import java.util.Set;

public class MaximumXOROfTwoNumbersInAnArray {
	public int findMaximumXOR(int[] nums) {
		int max = 0;
		int mask = 0;
		for (int i = 30; i >= 0; i--) {
			mask |= 1 << i;

			Set<Integer> prefixes = new HashSet<Integer>();
			for (int num : nums) {
				prefixes.add(num & mask);
			}

			int temp = max | (1 << i);
			for (int prefix : prefixes) {
				if (prefixes.contains(prefix ^ temp)) {
					max = temp;
					break;
				}
			}
		}
		return max;
	}
}
