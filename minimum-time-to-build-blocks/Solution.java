import java.util.PriorityQueue;

public class Solution {
	public int minBuildTime(int[] blocks, int split) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int block : blocks) {
			pq.offer(block);
		}

		while (pq.size() > 1) {
			pq.offer(Math.max(pq.poll(), pq.poll()) + split);
		}

		return pq.peek();
	}
}
