import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Solution {
  public int minimumTime(int n, int[][] relations, int[] time) {
    @SuppressWarnings("unchecked")
    List<Integer>[] nexts = new List[n];
    for (int i = 0; i < nexts.length; ++i) {
      nexts[i] = new ArrayList<>();
    }
    int[] prevCounts = new int[n];
    for (int[] relation : relations) {
      nexts[relation[0] - 1].add(relation[1] - 1);
      ++prevCounts[relation[1] - 1];
    }

    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.startTime));
    for (int i = 0; i < prevCounts.length; ++i) {
      if (prevCounts[i] == 0) {
        pq.offer(new Element(0, i));
      }
    }

    int result = 0;
    while (!pq.isEmpty()) {
      Element head = pq.poll();
      --prevCounts[head.course];
      if (prevCounts[head.course] <= 0) {
        result = Math.max(result, head.startTime + time[head.course]);
        for (int next : nexts[head.course]) {
          pq.offer(new Element(head.startTime + time[head.course], next));
        }
      }
    }

    return result;
  }
}

class Element {
  int startTime;
  int course;

  Element(int startTime, int course) {
    this.startTime = startTime;
    this.course = course;
  }
}
