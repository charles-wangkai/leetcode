import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int minSelection(int[][] availableIntervals, int[][] targetIntervals) {
    Arrays.sort(availableIntervals, Comparator.comparing(interval -> interval[0]));

    PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparing(Interval::left));
    for (int[] targetInterval : targetIntervals) {
      pq.offer(new Interval(targetInterval[0], targetInterval[1]));
    }

    int result = 0;
    int coveredRight = Integer.MIN_VALUE;
    int index = 0;
    while (!pq.isEmpty()) {
      Interval target = pq.poll();
      if (target.right() > coveredRight) {
        if (target.left() < coveredRight) {
          pq.offer(new Interval(coveredRight, target.right()));
        } else {
          int nextCoveredRight = coveredRight;
          while (index != availableIntervals.length
              && availableIntervals[index][0] <= target.left()) {
            nextCoveredRight = Math.max(nextCoveredRight, availableIntervals[index][1]);
            ++index;
          }
          if (nextCoveredRight == coveredRight) {
            return -1;
          }

          ++result;

          coveredRight = nextCoveredRight;

          if (coveredRight < target.right()) {
            pq.offer(new Interval(Math.max(coveredRight, target.left()), target.right()));
          }
        }
      }
    }

    return result;
  }
}

record Interval(int left, int right) {}
