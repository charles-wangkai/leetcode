import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
  public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
    PriorityQueue<Element> pq = new PriorityQueue<>(Comparator.comparing(e -> e.time));
    for (int[] tire : tires) {
      pq.offer(new Element(tire[0], 1, tire[0] * tire[1], tire[1]));
    }

    int limit =
        Arrays.stream(tires).mapToInt(tire -> tire[0]).min().getAsInt() * numLaps
            + (numLaps - 1) * changeTime;

    Map<Integer, Integer> lapToMinTime = new HashMap<>();
    Element prev = null;
    while (true) {
      Element head = pq.poll();
      if (head.time > limit) {
        break;
      }

      if (!lapToMinTime.containsKey(head.lapNum)) {
        lapToMinTime.put(head.lapNum, (int) head.time);
      }

      if (prev == null
          || head.lapNum != prev.lapNum
          || head.current < prev.current
          || head.r < prev.r) {
        pq.offer(
            new Element(head.time + head.current, head.lapNum + 1, head.current * head.r, head.r));
      }

      prev = head;
    }

    int[] dp = new int[numLaps + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i < dp.length; ++i) {
      for (int lap : lapToMinTime.keySet()) {
        if (lap <= i) {
          dp[i] =
              Math.min(dp[i], dp[i - lap] + lapToMinTime.get(lap) + ((lap == i) ? 0 : changeTime));
        }
      }
    }

    return dp[numLaps];
  }
}

class Element {
  long time;
  int lapNum;
  long current;
  int r;

  Element(long time, int lapNum, long current, int r) {
    this.time = time;
    this.lapNum = lapNum;
    this.current = current;
    this.r = r;
  }
}