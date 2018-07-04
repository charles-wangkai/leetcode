import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
	PriorityQueue<Integer> lowerHalf = new PriorityQueue<Integer>(Collections.reverseOrder());
	PriorityQueue<Integer> upperHalf = new PriorityQueue<Integer>();

	// Adds a number into the data structure.
	public void addNum(int num) {
		if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
			lowerHalf.offer(num);
		} else {
			upperHalf.offer(num);
		}

		if (lowerHalf.size() >= upperHalf.size() + 2) {
			upperHalf.offer(lowerHalf.poll());
		} else if (upperHalf.size() >= lowerHalf.size() + 2) {
			lowerHalf.offer(upperHalf.poll());
		}
	}

	// Returns the median of current data stream
	public double findMedian() {
		if (lowerHalf.size() == upperHalf.size()) {
			return ((double) lowerHalf.peek() + upperHalf.peek()) / 2;
		} else if (lowerHalf.size() > upperHalf.size()) {
			return lowerHalf.peek();
		} else {
			return upperHalf.peek();
		}
	}
}

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder mf = new MedianFinder();
// mf.addNum(1);
// mf.findMedian();