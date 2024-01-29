import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maximumLength(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return valueToCount.keySet().stream()
        .map(
            value -> {
              if (value == 1) {
                return valueToCount.get(1) - 1 + valueToCount.get(1) % 2;
              }

              int result = 1;
              for (long i = value;
                  i <= Integer.MAX_VALUE
                      && valueToCount.get((int) i) >= 2
                      && i * i <= Integer.MAX_VALUE
                      && valueToCount.containsKey((int) (i * i));
                  i *= i) {
                result += 2;
              }

              return result;
            })
        .mapToInt(Integer::intValue)
        .max()
        .getAsInt();
  }
}