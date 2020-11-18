import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int[][] merge(int[][] intervals) {
    int[][] sortedIntervals =
        Arrays.stream(intervals)
            .sorted((i1, i2) -> Integer.compare(i1[0], i2[0]))
            .toArray(int[][]::new);

    List<int[]> merged = new ArrayList<int[]>();
    for (int[] interval : sortedIntervals) {
      if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
        merged.add(interval);
      } else {
        int[] last = merged.get(merged.size() - 1);
        if (last[1] < interval[1]) {
          merged.remove(merged.size() - 1);
          merged.add(new int[] {last[0], interval[1]});
        }
      }
    }

    return merged.stream().toArray(int[][]::new);
  }
}
