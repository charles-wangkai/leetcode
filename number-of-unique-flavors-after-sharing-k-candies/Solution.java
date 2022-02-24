import java.util.HashMap;
import java.util.Map;

class Solution {
  public int shareCandies(int[] candies, int k) {
    Map<Integer, Integer> candyToCount = new HashMap<>();
    for (int i = 0; i < candies.length - k; ++i) {
      update(candyToCount, candies[i], 1);
    }

    int result = candyToCount.size();
    for (int leftIndex = candies.length - k - 1; leftIndex >= 0; --leftIndex) {
      update(candyToCount, candies[leftIndex], -1);
      update(candyToCount, candies[leftIndex + k], 1);

      result = Math.max(result, candyToCount.size());
    }

    return result;
  }

  void update(Map<Integer, Integer> candyToCount, int candy, int delta) {
    candyToCount.put(candy, candyToCount.getOrDefault(candy, 0) + delta);
    candyToCount.remove(candy, 0);
  }
}