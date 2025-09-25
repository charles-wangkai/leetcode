import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minConnectedGroups(int[][] intervals, int k) {
    Arrays.sort(intervals, Comparator.comparing(interval -> interval[0]));

    List<Range> ranges = new ArrayList<>();
    for (int[] interval : intervals) {
      if (ranges.isEmpty() || interval[0] > ranges.getLast().end) {
        ranges.add(new Range(interval[0], interval[1]));
      } else if (interval[1] > ranges.getLast().end) {
        ranges.getLast().end = interval[1];
      }
    }

    Range[] gaps =
        IntStream.range(0, ranges.size() - 1)
            .mapToObj(i -> new Range(ranges.get(i).end, ranges.get(i + 1).begin))
            .toArray(Range[]::new);

    int result = gaps.length + 1;
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < gaps.length; ++beginIndex) {
      while (endIndex != gaps.length - 1 && gaps[endIndex + 1].end - gaps[beginIndex].begin <= k) {
        ++endIndex;
      }

      result = Math.min(result, gaps.length - (endIndex - beginIndex + 1) + 1);
    }

    return result;
  }
}

class Range {
  int begin;
  int end;

  Range(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }
}
