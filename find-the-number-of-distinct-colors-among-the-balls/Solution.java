import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] queryResults(int limit, int[][] queries) {
    int[] result = new int[queries.length];
    Map<Integer, Integer> labelToColor = new HashMap<>();
    Map<Integer, Integer> colorToCount = new HashMap<>();
    for (int i = 0; i < result.length; ++i) {
      if (labelToColor.containsKey(queries[i][0])) {
        updateMap(colorToCount, labelToColor.get(queries[i][0]), -1);
      }
      updateMap(colorToCount, queries[i][1], 1);

      labelToColor.put(queries[i][0], queries[i][1]);

      result[i] = colorToCount.size();
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> colorToCount, int color, int delta) {
    colorToCount.put(color, colorToCount.getOrDefault(color, 0) + delta);
    colorToCount.remove(color, 0);
  }
}