import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minTaps(int n, int[] ranges) {
    Interval[] intervals =
        IntStream.range(0, ranges.length)
            .mapToObj(i -> new Interval(i - ranges[i], i + ranges[i]))
            .sorted(Comparator.comparing(Interval::left))
            .toArray(Interval[]::new);

    int maxRight = 0;
    int nextMaxRight = 0;
    int index = 0;
    int result = 0;
    while (maxRight < n) {
      while (index != intervals.length && intervals[index].left() <= maxRight) {
        nextMaxRight = Math.max(nextMaxRight, intervals[index].right());
        ++index;
      }

      if (nextMaxRight <= maxRight) {
        break;
      }

      ++result;
      maxRight = nextMaxRight;
    }

    if (maxRight < n) {
      return -1;
    }

    return result;
  }
}

record Interval(int left, int right) {}
