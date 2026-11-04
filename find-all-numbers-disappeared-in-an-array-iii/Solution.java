import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int[][] findDisappearedNumbers(int[] nums, int lower, int upper) {
    int lower_ = lower;
    int[] sorted =
        Arrays.stream(nums).filter(x -> x >= lower_ && x <= upper).distinct().sorted().toArray();

    List<Range> ranges = new ArrayList<>();
    int index = 0;
    while (lower <= upper) {
      if (index == sorted.length) {
        ranges.add(new Range(lower, upper));

        break;
      }

      if (sorted[index] == lower) {
        ++index;
        ++lower;
      } else {
        ranges.add(new Range(lower, sorted[index] - 1));
        lower = sorted[index];
      }
    }

    return ranges.stream()
        .map(range -> new int[] {range.begin(), range.end()})
        .toArray(int[][]::new);
  }
}

record Range(int begin, int end) {}
