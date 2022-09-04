import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {
    int n = chargeTimes.length;

    int result = 0;
    int endIndex = -1;
    SortedMap<Integer, Integer> chargeTimeToCount = new TreeMap<>();
    long runningCostSum = 0;
    for (int beginIndex = 0; beginIndex < n; ++beginIndex) {
      while (chargeTimeToCount.isEmpty()
          || chargeTimeToCount.lastKey() + (endIndex - beginIndex + 1) * runningCostSum <= budget) {
        result = Math.max(result, endIndex - beginIndex + 1);

        if (endIndex + 1 == n) {
          break;
        }

        ++endIndex;
        updateMap(chargeTimeToCount, chargeTimes[endIndex], 1);
        runningCostSum += runningCosts[endIndex];
      }

      updateMap(chargeTimeToCount, chargeTimes[beginIndex], -1);
      runningCostSum -= runningCosts[beginIndex];
    }

    return result;
  }

  void updateMap(SortedMap<Integer, Integer> chargeTimeToCount, int chargeTime, int delta) {
    chargeTimeToCount.put(chargeTime, chargeTimeToCount.getOrDefault(chargeTime, 0) + delta);
    chargeTimeToCount.remove(chargeTime, 0);
  }
}