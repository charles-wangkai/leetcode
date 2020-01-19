import java.util.Collections;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Solution {
	public int minTaps(int n, int[] ranges) {
		Interval[] intervals = IntStream.range(0, ranges.length)
				.mapToObj(i -> new Interval(i - ranges[i], i + ranges[i]))
				.sorted((i1, i2) -> Integer.compare(i1.left, i2.left)).toArray(Interval[]::new);

		int maxRight = 0;
		int index = 0;
		int result = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		while (maxRight < n) {
			while (index != intervals.length && intervals[index].left <= maxRight) {
				pq.offer(intervals[index].right);
				++index;
			}

			if (pq.isEmpty() || pq.peek() <= maxRight) {
				break;
			}

			++result;
			maxRight = pq.poll();
			pq.clear();
		}

		if (maxRight < n) {
			return -1;
		}

		return result;
	}
}

class Interval {
	int left;
	int right;

	Interval(int left, int right) {
		this.left = left;
		this.right = right;
	}
}