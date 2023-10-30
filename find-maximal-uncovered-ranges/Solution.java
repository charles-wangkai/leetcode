import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
  public int[][] findMaximalUncoveredRanges(int n, int[][] ranges) {
    Arrays.sort(ranges, Comparator.comparing(range -> range[0]));

    List<int[]> result = new ArrayList<>();
    int covered = -1;
    for (int[] range : ranges) {
      if (range[0] > covered + 1) {
        result.add(new int[] {covered + 1, range[0] - 1});
      }

      covered = Math.max(covered, range[1]);
    }
    if (covered != n - 1) {
      result.add(new int[] {covered + 1, n - 1});
    }

    return result.toArray(int[][]::new);
  }
}
