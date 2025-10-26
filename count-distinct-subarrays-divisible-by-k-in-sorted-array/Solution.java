import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public long numGoodSubarrays(int[] nums, int k) {
    long result = 0;
    int remainder = 0;
    Map<Integer, Integer> remainderToCount = new HashMap<>();
    remainderToCount.put(0, 1);
    int prev = -1;
    List<Integer> remainders = new ArrayList<>();
    for (int value : nums) {
      if (value != prev) {
        for (int r : remainders) {
          remainderToCount.put(r, remainderToCount.getOrDefault(r, 0) + 1);
        }

        remainders.clear();
      }

      remainder = (remainder + value) % k;
      result += remainderToCount.getOrDefault(remainder, 0);
      remainders.add(remainder);

      prev = value;
    }

    return result;
  }
}