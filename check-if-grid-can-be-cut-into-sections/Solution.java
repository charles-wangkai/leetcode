import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public boolean checkValidCuts(int n, int[][] rectangles) {
    return check(
            Arrays.stream(rectangles)
                .map(rectangle -> new Range(rectangle[0], rectangle[2]))
                .toArray(Range[]::new))
        || check(
            Arrays.stream(rectangles)
                .map(rectangle -> new Range(rectangle[1], rectangle[3]))
                .toArray(Range[]::new));
  }

  boolean check(Range[] ranges) {
    Arrays.sort(ranges, Comparator.comparing(Range::begin));

    int cutCount = -1;
    int max = 0;
    for (Range range : ranges) {
      if (range.begin() >= max) {
        ++cutCount;
      }

      max = Math.max(max, range.end());
    }

    return cutCount >= 2;
  }
}

record Range(int begin, int end) {}
