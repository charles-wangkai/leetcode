import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public long maximumSum(List<Integer> nums) {
    Map<String, Long> keyToSum = new HashMap<>();
    for (int i = 0; i < nums.size(); ++i) {
      String key = buildKey(i + 1);
      keyToSum.put(key, keyToSum.getOrDefault(key, 0L) + nums.get(i));
    }

    return keyToSum.values().stream().mapToLong(Long::longValue).max().getAsLong();
  }

  String buildKey(int value) {
    List<Integer> oddPoweredPrimes = new ArrayList<>();
    for (int i = 2; i * i <= value; ++i) {
      int exponent = 0;
      while (value % i == 0) {
        value /= i;
        ++exponent;
      }
      if (exponent % 2 == 1) {
        oddPoweredPrimes.add(i);
      }
    }
    if (value != 1) {
      oddPoweredPrimes.add(value);
    }

    return oddPoweredPrimes.stream().map(String::valueOf).collect(Collectors.joining(","));
  }
}
