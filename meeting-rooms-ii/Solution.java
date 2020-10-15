import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
  public int minMeetingRooms(int[][] intervals) {
    Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);

    int maxMeetingNum = 0;
    PriorityQueue<Integer> ends = new PriorityQueue<>();
    for (int[] interval : intervals) {
      while (!ends.isEmpty() && ends.peek() <= interval[0]) {
        ends.poll();
      }
      ends.offer(interval[1]);
      maxMeetingNum = Math.max(maxMeetingNum, ends.size());
    }

    return maxMeetingNum;
  }
}
