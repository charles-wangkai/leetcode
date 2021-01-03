import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int MODULUS = 1_000_000_007;
  static final int LIMIT = 1 << 21;

  public int countPairs(int[] deliciousness) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : deliciousness) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    long result = 0;
    for (int value : valueToCount.keySet()) {
      for (int sum = 1; sum <= LIMIT; sum *= 2) {
        int other = sum - value;
        if (other == value) {
          result += valueToCount.get(value) * (valueToCount.get(value) - 1L);
        } else {
          result += (long) valueToCount.get(value) * valueToCount.getOrDefault(other, 0);
        }
      }
    }
    result /= 2;

    return (int) (result % MODULUS);
  }
}
