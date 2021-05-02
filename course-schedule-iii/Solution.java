import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
  public int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, Comparator.comparing(course -> course[1]));

    PriorityQueue<Integer> times = new PriorityQueue<>(Comparator.reverseOrder());
    int start = 0;
    for (int[] course : courses) {
      start += course[0];
      times.offer(course[0]);

      while (start > course[1]) {
        start -= times.poll();
      }
    }

    return times.size();
  }
}
