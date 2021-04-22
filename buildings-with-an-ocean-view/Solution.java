import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int[] findBuildings(int[] heights) {
    List<Integer> result = new ArrayList<>();
    int maxHeight = 0;
    for (int i = heights.length - 1; i >= 0; --i) {
      if (heights[i] > maxHeight) {
        result.add(i);
        maxHeight = heights[i];
      }
    }
    Collections.reverse(result);

    return result.stream().mapToInt(x -> x).toArray();
  }
}
