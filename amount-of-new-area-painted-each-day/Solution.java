import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

class Solution {
  public int[] amountPainted(int[][] paint) {
    NavigableSet<Range> ranges = new TreeSet<>(Comparator.comparing(r -> r.begin));
    int[] result = new int[paint.length];
    for (int i = 0; i < paint.length; ++i) {
      Range first = ranges.lower(new Range(paint[i][0], -1));
      if (first != null && first.end < paint[i][0]) {
        first = null;
      }
      if (first == null || first.end < paint[i][1]) {
        int begin;
        int prev;
        if (first == null) {
          begin = paint[i][0];
          prev = paint[i][0];
        } else {
          begin = first.begin;
          prev = first.end;
          ranges.remove(first);
        }

        int end = paint[i][1];
        List<Range> toRemove = new ArrayList<>();
        for (Range range : ranges.tailSet(new Range(paint[i][0], -1))) {
          if (range.begin > paint[i][1]) {
            break;
          }

          result[i] += range.begin - prev;
          prev = range.end;
          end = Math.max(end, range.end);
          toRemove.add(range);
        }
        result[i] += Math.max(0, paint[i][1] - prev);

        ranges.removeAll(toRemove);
        ranges.add(new Range(begin, end));
      }
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