import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int eatenApples(int[] apples, int[] days) {
    int result = 0;
    int currentDay = 0;
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.lastDay));
    for (int i = 0; i < apples.length; ++i) {
      pq.offer(new Element(apples[i], currentDay + days[i] - 1));
      while (!pq.isEmpty() && pq.peek().lastDay < currentDay) {
        pq.poll();
      }
      if (!pq.isEmpty()) {
        ++result;
        --pq.peek().apple;
        if (pq.peek().apple == 0) {
          pq.poll();
        }
      }

      ++currentDay;
    }

    while (!pq.isEmpty()) {
      if (pq.peek().lastDay < currentDay) {
        pq.poll();
      } else {
        int eaten = Math.min(pq.peek().lastDay - currentDay + 1, pq.peek().apple);
        result += eaten;
        pq.peek().apple -= eaten;
        if (pq.peek().apple == 0) {
          pq.poll();
        }
        currentDay += eaten;
      }
    }

    return result;
  }
}

class Element {
  int apple;
  int lastDay;

  Element(int apple, int lastDay) {
    this.apple = apple;
    this.lastDay = lastDay;
  }
}
