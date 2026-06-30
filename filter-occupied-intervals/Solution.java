import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
  public List<List<Integer>> filterOccupiedIntervals(
      int[][] occupiedIntervals, int freeStart, int freeEnd) {
    List<Interval> removed =
        Arrays.stream(occupiedIntervals)
            .flatMap(occupiedInterval -> remove(freeStart, freeEnd, occupiedInterval).stream())
            .sorted(Comparator.comparing(interval -> interval.left))
            .toList();

    List<Interval> merged = new ArrayList<>();
    for (Interval interval : removed) {
      if (merged.isEmpty() || interval.left > merged.getLast().right + 1) {
        merged.add(interval);
      } else {
        merged.getLast().right = Math.max(merged.getLast().right, interval.right);
      }
    }

    return merged.stream().map(interval -> List.of(interval.left, interval.right)).toList();
  }

  List<Interval> remove(int freeStart, int freeEnd, int[] occupiedInterval) {
    if (occupiedInterval[0] > freeEnd || occupiedInterval[1] < freeStart) {
      return List.of(new Interval(occupiedInterval[0], occupiedInterval[1]));
    }
    if (occupiedInterval[0] >= freeStart && occupiedInterval[1] <= freeEnd) {
      return List.of();
    }

    List<Interval> result = new ArrayList<>();
    if (occupiedInterval[0] < freeStart) {
      result.add(new Interval(occupiedInterval[0], freeStart - 1));
    }
    if (occupiedInterval[1] > freeEnd) {
      result.add(new Interval(freeEnd + 1, occupiedInterval[1]));
    }

    return result;
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
