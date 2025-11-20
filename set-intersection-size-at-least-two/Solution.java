import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public int intersectionSizeTwo(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));

    List<Range> ranges = new ArrayList<>();
    for (int[] interval : intervals) {
      int left = interval[0];
      int right = interval[1];

      while (!ranges.isEmpty() && ranges.getLast().right() >= right) {
        ranges.removeLast();
      }

      if (ranges.isEmpty() || left != ranges.getLast().left()) {
        ranges.add(new Range(left, right));
      }
    }

    Set<Integer> set = new HashSet<>();
    for (Range range : ranges) {
      int chosenNum =
          (int) set.stream().filter(x -> x >= range.left() && x <= range.right()).count();

      if (chosenNum == 0) {
        set.add(range.right());
        set.add(range.right() - 1);
      } else if (chosenNum == 1) {
        set.add(range.right());
      }
    }

    return set.size();
  }
}

record Range(int left, int right) {}
