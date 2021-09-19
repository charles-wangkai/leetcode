import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int[] findOriginalArray(int[] changed) {
    SortedMap<Integer, Integer> valueToCount = new TreeMap<>();
    for (int value : changed) {
      updateMap(valueToCount, value, 1);
    }

    List<Integer> result = new ArrayList<>();
    while (!valueToCount.isEmpty()) {
      int original = valueToCount.firstKey();
      updateMap(valueToCount, original, -1);
      result.add(original);

      int doubled = original * 2;
      if (!valueToCount.containsKey(doubled)) {
        return new int[0];
      }
      updateMap(valueToCount, doubled, -1);
    }

    return result.stream().mapToInt(x -> x).toArray();
  }

  void updateMap(SortedMap<Integer, Integer> valueToCount, int value, int delta) {
    valueToCount.put(value, valueToCount.getOrDefault(value, 0) + delta);
    valueToCount.remove(value, 0);
  }
}
