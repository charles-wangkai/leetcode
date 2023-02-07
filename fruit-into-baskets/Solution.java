import java.util.HashMap;
import java.util.Map;

class Solution {
  public int totalFruit(int[] fruits) {
    int result = 0;
    Map<Integer, Integer> typeToCount = new HashMap<>();
    int leftIndex = 0;
    for (int rightIndex = 0; rightIndex < fruits.length; ++rightIndex) {
      updateMap(typeToCount, fruits[rightIndex], 1);
      while (typeToCount.size() == 3) {
        updateMap(typeToCount, fruits[leftIndex], -1);
        ++leftIndex;
      }

      result = Math.max(result, rightIndex - leftIndex + 1);
    }

    return result;
  }

  void updateMap(Map<Integer, Integer> typeToCount, int type, int delta) {
    typeToCount.put(type, typeToCount.getOrDefault(type, 0) + delta);
    typeToCount.remove(type, 0);
  }
}
