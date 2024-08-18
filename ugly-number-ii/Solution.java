import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  static final int[] FACTORS = {2, 3, 5};

  public int nthUglyNumber(int n) {
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(Element::value));
    pq.offer(new Element(1, 0));

    for (int i = 0; i < n - 1; ++i) {
      Element head = pq.poll();

      for (int j = head.beginFactorIndex(); j < FACTORS.length; ++j) {
        pq.offer(new Element(head.value() * FACTORS[j], j));
      }
    }

    return (int) pq.poll().value();
  }
}

record Element(long value, int beginFactorIndex) {}
