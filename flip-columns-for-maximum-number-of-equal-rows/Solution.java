import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int maxEqualRowsAfterFlips(int[][] matrix) {
    Map<List<Integer>, Integer> patternToCount = new HashMap<>();
    for (int[] line : matrix) {
      List<Integer> pattern = new ArrayList<>();
      for (int c = 1; c < line.length; ++c) {
        pattern.add(line[c] ^ line[0]);
      }

      patternToCount.put(pattern, patternToCount.getOrDefault(pattern, 0) + 1);
    }

    return patternToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();
  }
}
