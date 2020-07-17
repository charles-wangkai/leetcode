import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> valueToCount = new HashMap<>();
		for (int value : nums) {
			valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
		}

		PriorityQueue<Integer> pq = new PriorityQueue<>(
				(v1, v2) -> Integer.compare(valueToCount.get(v1), valueToCount.get(v2)));
		for (int value : valueToCount.keySet()) {
			pq.offer(value);

			if (pq.size() == k + 1) {
				pq.poll();
			}
		}

		return pq.stream().mapToInt(x -> x).toArray();
	}
}