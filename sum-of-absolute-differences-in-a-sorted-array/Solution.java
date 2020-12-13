import java.util.Arrays;

class Solution {
  public int[] getSumAbsoluteDifferences(int[] nums) {
    int[] result = new int[nums.length];
    result[0] = Arrays.stream(nums).map(num -> num - nums[0]).sum();

    for (int i = 1; i < result.length; ++i) {
      result[i] = result[i - 1] + (nums[i] - nums[i - 1]) * (i - (nums.length - i));
    }

    return result;
  }
}
