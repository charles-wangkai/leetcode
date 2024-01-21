import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long minimumCost(int[] nums, int k, int dist) {
    Inventory inventory = new Inventory(k - 2);
    for (int i = 2; i < dist + 1; ++i) {
      inventory.add(nums[i]);
    }

    long result = Long.MAX_VALUE;
    for (int i = 1; i <= nums.length - k + 1; ++i) {
      if (i + dist < nums.length) {
        inventory.add(nums[i + dist]);
      }

      result = Math.min(result, nums[0] + nums[i] + inventory.getMinSum());

      inventory.remove(nums[i + 1]);
    }

    return result;
  }
}

class Inventory {
  int needed;
  SortedMap<Integer, Integer> lowerValueToCount = new TreeMap<>();
  int lowerSize = 0;
  long lowerSum = 0;
  SortedMap<Integer, Integer> upperValueToCount = new TreeMap<>();

  Inventory(int needed) {
    this.needed = needed;
  }

  void add(int value) {
    if (lowerSize != needed) {
      updateMap(lowerValueToCount, value, 1);
      ++lowerSize;
      lowerSum += value;
    } else {
      int lowerMaxValue = lowerValueToCount.lastKey();
      if (value >= lowerMaxValue) {
        updateMap(upperValueToCount, value, 1);
      } else {
        updateMap(lowerValueToCount, lowerMaxValue, -1);
        updateMap(lowerValueToCount, value, 1);
        lowerSum += value - lowerMaxValue;

        updateMap(upperValueToCount, lowerMaxValue, 1);
      }
    }
  }

  void remove(int value) {
    if (upperValueToCount.containsKey(value)) {
      updateMap(upperValueToCount, value, -1);
    } else if (upperValueToCount.isEmpty()) {
      updateMap(lowerValueToCount, value, -1);
      --lowerSize;
      lowerSum -= value;
    } else {
      int upperMinValue = upperValueToCount.firstKey();

      updateMap(lowerValueToCount, value, -1);
      updateMap(lowerValueToCount, upperMinValue, 1);
      lowerSum += upperMinValue - value;

      updateMap(upperValueToCount, upperMinValue, -1);
    }
  }

  long getMinSum() {
    return lowerSum;
  }

  private void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}