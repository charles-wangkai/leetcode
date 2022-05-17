import java.util.Comparator;
import java.util.NavigableSet;
import java.util.TreeSet;

class CountIntervals {
  NavigableSet<Interval> intervals = new TreeSet<>(Comparator.comparing(interval -> interval.left));
  int sum;

  public void add(int left, int right) {
    Interval lower = intervals.floor(new Interval(left, -1));
    if (lower != null && lower.right >= left) {
      intervals.remove(lower);
      sum -= lower.right - lower.left + 1;

      left = lower.left;
      right = Math.max(right, lower.right);
    }

    while (true) {
      Interval covered = intervals.floor(new Interval(right, -1));
      if (covered == null || covered.right < left) {
        break;
      }

      intervals.remove(covered);
      sum -= covered.right - covered.left + 1;

      if (covered.right > right) {
        intervals.add(new Interval(right + 1, covered.right));
        sum += covered.right - right;
      }
    }

    intervals.add(new Interval(left, right));
    sum += right - left + 1;
  }

  public int count() {
    return sum;
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

// Your CountIntervals object will be instantiated and called as such:
// CountIntervals obj = new CountIntervals();
// obj.add(left,right);
// int param_2 = obj.count();
