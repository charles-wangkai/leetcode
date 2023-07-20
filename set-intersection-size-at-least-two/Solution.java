import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution {
  public int intersectionSizeTwo(int[][] intervals) {
    Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));

    Deque<Interval> stack = new ArrayDeque<>();
    for (int[] interval : intervals) {
      int left = interval[0];
      int right = interval[1];

      while (!stack.isEmpty() && stack.peek().right >= right) {
        stack.pop();
      }

      if (stack.isEmpty() || stack.peek().left < left) {
        stack.push(new Interval(left, right));
      }
    }

    Set<Integer> set = new HashSet<>();
    for (Interval interval : stack) {
      int chosenNum =
          set.stream().mapToInt(n -> (n >= interval.left && n <= interval.right) ? 1 : 0).sum();

      if (chosenNum == 0) {
        set.add(interval.right);
        set.add(interval.right - 1);
      } else if (chosenNum == 1) {
        set.add(interval.right);
      }
    }

    return set.size();
  }
}

class Interval {
  int left;
  int right;

  Interval(int left, int right) {
    this.left = left;
    this.right = right;
  }
}
