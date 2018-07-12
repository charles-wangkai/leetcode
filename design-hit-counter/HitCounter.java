import java.util.Deque;
import java.util.LinkedList;

public class HitCounter {
	private static final int MAX_TIMESTAMP_DIFF = 300;

	private Deque<Element> queue;
	private int totalCount;

	/** Initialize your data structure here. */
	public HitCounter() {
		queue = new LinkedList<Element>();
		totalCount = 0;
	}

	/**
	 * Record a hit.
	 * 
	 * @param timestamp
	 *            - The current timestamp (in seconds granularity).
	 */
	public void hit(int timestamp) {
		if (queue.isEmpty() || queue.getLast().timestamp != timestamp) {
			queue.addLast(new Element(timestamp, 1));
		} else {
			queue.getLast().count++;
		}
		totalCount++;
	}

	/**
	 * Return the number of hits in the past 5 minutes.
	 * 
	 * @param timestamp
	 *            - The current timestamp (in seconds granularity).
	 */
	public int getHits(int timestamp) {
		while (!queue.isEmpty() && timestamp - queue.getFirst().timestamp >= MAX_TIMESTAMP_DIFF) {
			totalCount -= queue.getFirst().count;
			queue.removeFirst();
		}
		return totalCount;
	}
}

class Element {
	int timestamp;
	int count;

	Element(int timestamp, int count) {
		this.timestamp = timestamp;
		this.count = count;
	}
}

// Your HitCounter object will be instantiated and called as such:
// HitCounter obj = new HitCounter();
// obj.hit(timestamp);
// int param_2 = obj.getHits(timestamp);
