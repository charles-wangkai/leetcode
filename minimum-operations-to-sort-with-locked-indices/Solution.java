import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] nums, int[] locked, int k) {
    boolean[] unlockNeeded = new boolean[nums.length];

    int leftMax = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; ++i) {
      leftMax = Math.max(leftMax, nums[i]);
      if (locked[i] == 1 && leftMax > i + 1) {
        unlockNeeded[i] = true;
      }
    }

    int rightMin = Integer.MAX_VALUE;
    for (int i = nums.length - 1; i >= 0; --i) {
      rightMin = Math.min(rightMin, nums[i]);
      if (locked[i] == 1 && rightMin < i + 1) {
        unlockNeeded[i] = true;
      }
    }

    int unlockCount =
        (int) IntStream.range(0, unlockNeeded.length).filter(i -> unlockNeeded[i]).count();
    if (unlockCount > k) {
      return -1;
    }

    int result = unlockCount;
    for (int i = 0; i < nums.length; ++i) {
      for (int j = i + 1; j < nums.length; ++j) {
        if (nums[i] > nums[j]) {
          ++result;
        }
      }
    }

    return result;
  }
}