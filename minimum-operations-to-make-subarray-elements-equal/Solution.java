import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public long minOperations(int[] nums, int k) {
    long result = Long.MAX_VALUE;
    Balancer balancer = new Balancer();
    for (int i = 0; i < nums.length; ++i) {
      balancer.add(nums[i]);

      if (i >= k - 1) {
        result = Math.min(result, balancer.computeOperationNum());

        balancer.remove(nums[i - k + 1]);
      }
    }

    return result;
  }
}

class Balancer {
  SortedMap<Integer, Integer> lowerValueToCount = new TreeMap<>();
  int lowerSize = 0;
  long lowerSum = 0;

  SortedMap<Integer, Integer> upperValueToCount = new TreeMap<>();
  int upperSize = 0;
  long upperSum = 0;

  long computeOperationNum() {
    return upperSum - lowerSum - ((lowerSize == upperSize) ? 0 : upperValueToCount.firstKey());
  }

  void add(int x) {
    if (upperValueToCount.isEmpty() || x >= upperValueToCount.firstKey()) {
      addToUpper(x);
    } else {
      addToLower(x);
    }

    balance();
  }

  void remove(int x) {
    if (x >= upperValueToCount.firstKey()) {
      removeFromUpper(x);
    } else {
      removeFromLower(x);
    }

    balance();
  }

  void balance() {
    if (upperSize == lowerSize + 2) {
      int value = upperValueToCount.firstKey();
      removeFromUpper(value);
      addToLower(value);
    } else if (lowerSize == upperSize + 1) {
      int value = lowerValueToCount.lastKey();
      removeFromLower(value);
      addToUpper(value);
    }
  }

  void addToLower(int x) {
    lowerValueToCount.put(x, lowerValueToCount.getOrDefault(x, 0) + 1);
    ++lowerSize;
    lowerSum += x;
  }

  void addToUpper(int x) {
    upperValueToCount.put(x, upperValueToCount.getOrDefault(x, 0) + 1);
    ++upperSize;
    upperSum += x;
  }

  void removeFromLower(int x) {
    lowerValueToCount.put(x, lowerValueToCount.get(x) - 1);
    lowerValueToCount.remove(x, 0);

    --lowerSize;
    lowerSum -= x;
  }

  void removeFromUpper(int x) {
    upperValueToCount.put(x, upperValueToCount.get(x) - 1);
    upperValueToCount.remove(x, 0);

    --upperSize;
    upperSum -= x;
  }
}
