import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxEqualAdjacentPairs(int[] nums) {
    int sameCount = 0;
    Map<Pair, Integer> pairToCount = new HashMap<>();
    for (int i = 0; i < nums.length - 1; ++i) {
      if (nums[i] == nums[i + 1]) {
        ++sameCount;
      } else {
        Pair pair = new Pair(Math.min(nums[i], nums[i + 1]), Math.max(nums[i], nums[i + 1]));
        pairToCount.put(pair, pairToCount.getOrDefault(pair, 0) + 1);
      }
    }

    return sameCount + pairToCount.values().stream().mapToInt(Integer::intValue).max().orElse(0);
  }
}

record Pair(int value1, int value2) {}
