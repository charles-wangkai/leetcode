import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int numRescueBoats(int[] people, int limit) {
    NavigableMap<Integer, Integer> weightToCount = new TreeMap<>();
    for (int weight : people) {
      weightToCount.put(weight, weightToCount.getOrDefault(weight, 0) + 1);
    }

    int boatNum = 0;
    while (!weightToCount.isEmpty()) {
      ++boatNum;

      int maxWeight = weightToCount.lastKey();
      decreaseCount(weightToCount, maxWeight);

      Integer otherWeight = weightToCount.floorKey(limit - maxWeight);
      if (otherWeight != null) {
        decreaseCount(weightToCount, otherWeight);
      }
    }

    return boatNum;
  }

  void decreaseCount(NavigableMap<Integer, Integer> weightToCount, int weight) {
    weightToCount.put(weight, weightToCount.get(weight) - 1);
    weightToCount.remove(weight, 0);
  }
}
