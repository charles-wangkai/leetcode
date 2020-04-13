import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int findMaxLength(int[] nums) {
		int maxLength = 0;

		Map<Integer, Integer> deltaToFirstIndex = new HashMap<>();
		deltaToFirstIndex.put(0, -1);

		int delta = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				delta++;
			} else {
				delta--;
			}

			if (deltaToFirstIndex.containsKey(delta)) {
				maxLength = Math.max(maxLength, i - deltaToFirstIndex.get(delta));
			} else {
				deltaToFirstIndex.put(delta, i);
			}
		}

		return maxLength;
	}
}
