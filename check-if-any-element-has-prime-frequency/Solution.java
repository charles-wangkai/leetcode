import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean checkPrimeFrequency(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.values().stream().anyMatch(this::isPrime);
  }

  boolean isPrime(int x) {
    if (x < 2) {
      return false;
    }

    for (int i = 2; i * i <= x; ++i) {
      if (x % i == 0) {
        return false;
      }
    }

    return true;
  }
}