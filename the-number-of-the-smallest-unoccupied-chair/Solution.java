import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int smallestChair(int[][] times, int targetFriend) {
    PriorityQueue<Integer> unoccupied = new PriorityQueue<>();
    int minChairId = 0;
    PriorityQueue<Element> pq =
        new PriorityQueue<>(
            Comparator.comparing((Element e) -> e.time).thenComparing(e -> e.arriveOrLeave));
    for (int i = 0; i < times.length; ++i) {
      pq.offer(new Element(times[i][0], true, i));
      pq.offer(new Element(times[i][1], false, i));
    }

    int[] chairs = new int[times.length];
    while (true) {
      Element head = pq.poll();
      if (head.arriveOrLeave) {
        int chair;
        if (unoccupied.isEmpty()) {
          chair = minChairId;
          ++minChairId;
        } else {
          chair = unoccupied.poll();
        }

        if (head.friend == targetFriend) {
          return chair;
        }

        chairs[head.friend] = chair;
      } else {
        unoccupied.offer(chairs[head.friend]);
      }
    }
  }
}

class Element {
  int time;
  boolean arriveOrLeave;
  int friend;

  Element(int time, boolean arriveOrLeave, int friend) {
    this.time = time;
    this.arriveOrLeave = arriveOrLeave;
    this.friend = friend;
  }
}
