import java.util.HashMap;
import java.util.Map;

class Solution {
  public long coutPairs(int[] nums, int k) {
    Map<Integer, Integer> divisorToCount = new HashMap<>();
    for (int num : nums) {
      for (int i = 1; i * i <= num; ++i) {
        if (num % i == 0) {
          divisorToCount.put(i, divisorToCount.getOrDefault(i, 0) + 1);
          if (i * i != num) {
            divisorToCount.put(num / i, divisorToCount.getOrDefault(num / i, 0) + 1);
          }
        }
      }
    }

    long result = 0;
    for (int num : nums) {
      int rest = k / gcd(num, k);
      result += divisorToCount.getOrDefault(rest, 0) - ((num % rest == 0) ? 1 : 0);
    }
    result /= 2;

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}