import java.util.Arrays;

class Solution {
  public int xorAllNums(int[] nums1, int[] nums2) {
    return ((nums2.length % 2 == 0) ? 0 : computeXor(nums1))
        ^ ((nums1.length % 2 == 0) ? 0 : computeXor(nums2));
  }

  int computeXor(int[] nums) {
    return Arrays.stream(nums).reduce((x, y) -> x ^ y).getAsInt();
  }
}
