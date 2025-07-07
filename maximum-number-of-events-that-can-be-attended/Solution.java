import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int maxEvents(int[][] events) {
    Arrays.sort(events, Comparator.comparing(event -> event[0]));

    int result = 0;
    int eventIndex = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    int day = Integer.MIN_VALUE;
    while (eventIndex != events.length || !pq.isEmpty()) {
      while (eventIndex != events.length && events[eventIndex][0] == day) {
        pq.offer(events[eventIndex][1]);
        ++eventIndex;
      }

      while (!pq.isEmpty() && pq.peek() < day) {
        pq.poll();
      }

      if (!pq.isEmpty()) {
        pq.poll();
        ++day;
        ++result;
      } else if (eventIndex != events.length) {
        day = events[eventIndex][0];
      }
    }

    return result;
  }
}