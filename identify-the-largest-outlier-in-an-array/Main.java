import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int getLargestOutlier(int[] nums) {
    int total = Arrays.stream(nums).sum();

    Map<Integer, Integer> valueToCount = new HashMap<>();
    for (int num : nums) {
      valueToCount.put(num, valueToCount.getOrDefault(num, 0) + 1);
    }

    return Arrays.stream(nums)
        .filter(
            num -> {
              int doubleSum = total - num;
              if (doubleSum % 2 != 0) {
                return false;
              }

              int sum = doubleSum / 2;

              return valueToCount.getOrDefault(sum, 0) >= 1 + ((num == sum) ? 1 : 0);
            })
        .max()
        .getAsInt();
  }
}