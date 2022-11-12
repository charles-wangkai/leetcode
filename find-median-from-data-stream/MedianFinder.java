import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {
  PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Comparator.reverseOrder());
  PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

  public void addNum(int num) {
    if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
      lowerHalf.offer(num);
    } else {
      upperHalf.offer(num);
    }

    if (lowerHalf.size() == upperHalf.size() + 2) {
      upperHalf.offer(lowerHalf.poll());
    } else if (upperHalf.size() == lowerHalf.size() + 2) {
      lowerHalf.offer(upperHalf.poll());
    }
  }

  public double findMedian() {
    if (lowerHalf.size() > upperHalf.size()) {
      return lowerHalf.peek();
    } else if (lowerHalf.size() < upperHalf.size()) {
      return upperHalf.peek();
    }

    return ((double) lowerHalf.peek() + upperHalf.peek()) / 2;
  }
}

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder obj = new MedianFinder();
// obj.addNum(num);
// double param_2 = obj.findMedian();
