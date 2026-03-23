import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minRemovals(int[] nums, int target) {
    int leftLength = nums.length / 2;
    Map<Integer, Integer> xorToMaxNum = buildXorToMaxNum(nums, leftLength);

    int maxChosenNum = -1;
    int rightLength = nums.length - leftLength;
    for (int mask = 0; mask < 1 << rightLength; ++mask) {
      int xor = 0;
      for (int i = 0; i < rightLength; ++i) {
        if (((mask >> i) & 1) == 1) {
          xor ^= nums[i + leftLength];
        }
      }

      int other = target ^ xor;
      if (xorToMaxNum.containsKey(other)) {
        maxChosenNum = Math.max(maxChosenNum, xorToMaxNum.get(other) + Integer.bitCount(mask));
      }
    }

    return (maxChosenNum == -1) ? -1 : (nums.length - maxChosenNum);
  }

  Map<Integer, Integer> buildXorToMaxNum(int[] nums, int leftLength) {
    Map<Integer, Integer> xorToMaxNum = new HashMap<>();
    for (int mask = 0; mask < 1 << leftLength; ++mask) {
      int xor = 0;
      for (int i = 0; i < leftLength; ++i) {
        if (((mask >> i) & 1) == 1) {
          xor ^= nums[i];
        }
      }

      xorToMaxNum.put(xor, Math.max(xorToMaxNum.getOrDefault(xor, -1), Integer.bitCount(mask)));
    }

    return xorToMaxNum;
  }
}