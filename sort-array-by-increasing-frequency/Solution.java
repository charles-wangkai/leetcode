import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int[] frequencySort(int[] nums) {
    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return Arrays.stream(nums)
        .boxed()
        .sorted(
            (num1, num2) -> {
              int count1 = valueToCount.get(num1);
              int count2 = valueToCount.get(num2);

              return (count1 != count2)
                  ? Integer.compare(count1, count2)
                  : -Integer.compare(num1, num2);
            })
        .mapToInt(x -> x)
        .toArray();
  }
}
