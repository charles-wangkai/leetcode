import java.util.HashMap;
import java.util.Map;

class Solution {
  public int countSpecialIntegers(int[] nums) {
    Map<Integer, Integer> valueToBlockCount = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      if (i == 0 || nums[i] != nums[i - 1]) {
        valueToBlockCount.put(nums[i], valueToBlockCount.getOrDefault(nums[i], 0) + 1);
      }
    }

    return (int) valueToBlockCount.values().stream().filter(blockCount -> blockCount == 1).count();
  }
}