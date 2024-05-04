import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int numRescueBoats(int[] people, int limit) {
    NavigableMap<Integer, Integer> weightToCount = new TreeMap<>();
    for (int weight : people) {
      update(weightToCount, weight, 1);
    }

    int result = 0;
    while (!weightToCount.isEmpty()) {
      ++result;

      int maxWeight = weightToCount.lastKey();
      update(weightToCount, maxWeight, -1);

      Integer otherWeight = weightToCount.floorKey(limit - maxWeight);
      if (otherWeight != null) {
        update(weightToCount, otherWeight, -1);
      }
    }

    return result;
  }

  void update(NavigableMap<Integer, Integer> weightToCount, int weight, int delta) {
    weightToCount.put(weight, weightToCount.getOrDefault(weight, 0) + delta);
    weightToCount.remove(weight, 0);
  }
}
