import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minSetSize(int[] arr) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : arr) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    int[] counts =
        valueToCount.values().stream().sorted(Comparator.reverseOrder()).mapToInt(x -> x).toArray();

    int sum = 0;
    for (int i = 0; ; ++i) {
      sum += counts[i];

      if (sum * 2 >= arr.length) {
        return i + 1;
      }
    }
  }
}
