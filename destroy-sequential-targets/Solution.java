import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int destroyTargets(int[] nums, int space) {
    Map<Integer, Integer> remainderToCount = new HashMap<>();
    for (int num : nums) {
      int remainder = num % space;
      remainderToCount.put(remainder, remainderToCount.getOrDefault(remainder, 0) + 1);
    }

    int maxCount = remainderToCount.values().stream().mapToInt(x -> x).max().getAsInt();

    return Arrays.stream(nums)
        .filter(num -> remainderToCount.get(num % space) == maxCount)
        .min()
        .getAsInt();
  }
}
