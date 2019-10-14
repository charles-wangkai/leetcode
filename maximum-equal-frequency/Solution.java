import java.util.HashMap;
import java.util.Map;

public class Solution {
	public int maxEqualFreq(int[] nums) {
		int result = 0;
		Map<Integer, Integer> valueToFreq = new HashMap<>();
		Map<Integer, Integer> freqToCount = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			updateMap(valueToFreq, nums[i], 1);

			int freq = valueToFreq.get(nums[i]);
			if (freq != 1) {
				updateMap(freqToCount, freq - 1, -1);
			}
			updateMap(freqToCount, freq, 1);

			if (check(freqToCount)) {
				result = i + 1;
			}
		}

		return result;
	}

	void updateMap(Map<Integer, Integer> map, int key, int valueDelta) {
		map.put(key, map.getOrDefault(key, 0) + valueDelta);
		map.remove(key, 0);
	}

	boolean check(Map<Integer, Integer> freqToCount) {
		int size = freqToCount.size();

		if (size == 1) {
			return freqToCount.keySet().iterator().next() == 1 || freqToCount.values().iterator().next() == 1;
		} else if (size == 2) {
			int[] keys = freqToCount.keySet().stream().sorted().mapToInt(x -> x).toArray();

			return (keys[0] + 1 == keys[1] && freqToCount.get(keys[1]) == 1)
					|| (keys[0] == 1 && freqToCount.get(keys[0]) == 1);
		} else {
			return false;
		}
	}
}
