import java.util.HashMap;
import java.util.Map;

public class ContiguousArray {
	public int findMaxLength(int[] nums) {
		int maxLength = 0;

		Map<Integer, Integer> delta2firstIndex = new HashMap<Integer, Integer>();
		delta2firstIndex.put(0, -1);

		int delta = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == 0) {
				delta++;
			} else {
				delta--;
			}

			if (delta2firstIndex.containsKey(delta)) {
				maxLength = Math.max(maxLength, i - delta2firstIndex.get(delta));
			} else {
				delta2firstIndex.put(delta, i);
			}
		}

		return maxLength;
	}
}
