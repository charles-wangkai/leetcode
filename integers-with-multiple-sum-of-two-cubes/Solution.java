import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> findGoodIntegers(int n) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int a = 1; a * a * a <= n; ++a) {
      for (int b = a; a * a * a + b * b * b <= n; ++b) {
        int value = a * a * a + b * b * b;
        valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
      }
    }

    return valueToCount.keySet().stream()
        .filter(value -> valueToCount.get(value) >= 2)
        .sorted()
        .toList();
  }
}