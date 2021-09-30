import java.util.Arrays;

class Solution {
  public boolean canPartitionKSubsets(int[] nums, int k) {
    int sum = Arrays.stream(nums).sum();
    if (sum % k != 0) {
      return false;
    }

    int subsetSum = sum / k;
    if (Arrays.stream(nums).anyMatch(num -> num > subsetSum)) {
      return false;
    }

    Arrays.sort(nums);

    return search(nums, subsetSum, new boolean[nums.length], nums.length - 1, k, subsetSum);
  }

  boolean search(
      int[] nums, int subsetSum, boolean[] used, int startIndex, int remainSubset, int remainSum) {
    if (remainSubset == 0) {
      return true;
    }

    for (int i = startIndex; i >= 0; --i) {
      if (!used[i] && nums[i] <= remainSum) {
        used[i] = true;

        if (nums[i] == remainSum) {
          if (search(nums, subsetSum, used, nums.length - 1, remainSubset - 1, subsetSum)) {
            return true;
          }
        } else if (search(nums, subsetSum, used, i - 1, remainSubset, remainSum - nums[i])) {
          return true;
        }

        used[i] = false;
      }
    }

    return false;
  }
}
