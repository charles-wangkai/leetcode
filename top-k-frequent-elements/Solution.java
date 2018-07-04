import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> number2count = new HashMap<Integer, Integer>();
		for (int number : nums) {
			if (!number2count.containsKey(number)) {
				number2count.put(number, 0);
			}
			number2count.put(number, number2count.get(number) + 1);
		}

		PriorityQueue<NumberAndCount> pq = new PriorityQueue<NumberAndCount>((nc1, nc2) -> nc1.count - nc2.count);
		for (int number : number2count.keySet()) {
			int count = number2count.get(number);

			pq.offer(new NumberAndCount(number, count));

			if (pq.size() > k) {
				pq.poll();
			}
		}

		return pq.stream().map(nc -> nc.number).collect(Collectors.toList());
	}
}

class NumberAndCount {
	int number;
	int count;

	NumberAndCount(int number, int count) {
		this.number = number;
		this.count = count;
	}
}