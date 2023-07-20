import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

class SummaryRanges {
  private NavigableSet<Interval> intervals =
      new TreeSet<>(Comparator.comparing(interval -> interval.start));

  public void addNum(int value) {
    Interval toAdd = new Interval(value, value);
    Interval floor = intervals.floor(toAdd);
    Interval ceiling = intervals.ceiling(toAdd);

    if (!isWithin(value, floor) && !isWithin(value, ceiling)) {
      if (floor != null
          && ceiling != null
          && value == floor.end + 1
          && value == ceiling.start - 1) {
        intervals.remove(floor);
        intervals.remove(ceiling);
        intervals.add(new Interval(floor.start, ceiling.end));
      } else if (floor != null && value == floor.end + 1) {
        intervals.remove(floor);
        intervals.add(new Interval(floor.start, value));
      } else if (ceiling != null && value == ceiling.start - 1) {
        intervals.remove(ceiling);
        intervals.add(new Interval(value, ceiling.end));
      } else {
        intervals.add(toAdd);
      }
    }
  }

  private boolean isWithin(int value, Interval interval) {
    return interval != null && value >= interval.start && value <= interval.end;
  }

  public int[][] getIntervals() {
    return intervals.stream()
        .map(interval -> new int[] {interval.start, interval.end})
        .toArray(int[][]::new);
  }
}

class Interval {
  int start;
  int end;

  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

// Your SummaryRanges object will be instantiated and called as such:
// SummaryRanges obj = new SummaryRanges();
// obj.addNum(value);
// int[][] param_2 = obj.getIntervals();
