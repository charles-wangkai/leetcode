import java.util.ArrayList;
import java.util.List;

class Solution {
  public int[][] insert(int[][] intervals, int[] newInterval) {
    List<int[]> result = new ArrayList<>();
    int index = 0;
    boolean used = false;
    while (index != intervals.length || !used) {
      int[] selected;
      if (index != intervals.length && (used || intervals[index][0] <= newInterval[0])) {
        selected = intervals[index];
        ++index;
      } else {
        selected = newInterval;
        used = true;
      }

      if (result.isEmpty() || result.get(result.size() - 1)[1] < selected[0]) {
        result.add(selected);
      } else {
        int[] last = result.get(result.size() - 1);
        last[1] = Math.max(last[1], selected[1]);
      }
    }

    return result.toArray(int[][]::new);
  }
}
