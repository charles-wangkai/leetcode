import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int findLeastNumOfUniqueInts(int[] arr, int k) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int value : arr) {
      valueToCount.put(value, valueToCount.getOrDefault(value, 0) + 1);
    }

    return (int)
        Arrays.stream(arr)
            .boxed()
            .sorted(Comparator.<Integer, Integer>comparing(valueToCount::get).thenComparing(x -> x))
            .skip(k)
            .distinct()
            .count();
  }
}