import java.util.Arrays;

class Solution {
  public int[] getMaximumXor(int[] nums, int maximumBit) {
    int[] result = new int[nums.length];
    int prefixXor = Arrays.stream(nums).reduce((acc, x) -> acc ^ x).getAsInt();
    for (int i = 0; i < result.length; ++i) {
      result[i] = prefixXor ^ ((1 << maximumBit) - 1);
      prefixXor ^= nums[nums.length - 1 - i];
    }

    return result;
  }
}
