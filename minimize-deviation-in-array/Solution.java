import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int minimumDeviation(int[] nums) {
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.current));
    int max = -1;
    for (int num : nums) {
      int limit = (num % 2 == 0) ? num : (num * 2);
      int current = num;
      while (current % 2 == 0) {
        current /= 2;
      }

      max = Math.max(max, current);
      pq.offer(new Element(current, limit));
    }

    int result = Integer.MAX_VALUE;
    while (true) {
      Element head = pq.poll();
      result = Math.min(result, max - head.current);

      int next = head.current * 2;
      if (next > head.limit) {
        break;
      }

      max = Math.max(max, next);
      pq.offer(new Element(next, head.limit));
    }

    return result;
  }
}

class Element {
  int current;
  int limit;

  Element(int current, int limit) {
    this.current = current;
    this.limit = limit;
  }
}
