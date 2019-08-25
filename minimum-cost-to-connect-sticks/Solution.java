import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
	public int connectSticks(int[] sticks) {
		PriorityQueue<Integer> pq = Arrays.stream(sticks).collect(PriorityQueue::new, PriorityQueue::offer,
				PriorityQueue::addAll);

		int result = 0;
		while (pq.size() != 1) {
			int cost = pq.poll() + pq.poll();
			result += cost;

			pq.offer(cost);
		}

		return result;
	}
}
