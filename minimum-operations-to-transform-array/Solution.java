class Solution {
  public long minOperations(int[] nums1, int[] nums2) {
    long result = 1;
    int minDiff = Integer.MAX_VALUE;
    for (int i = 0; i < nums1.length; ++i) {
      int min = Math.min(nums1[i], nums2[i]);
      int max = Math.max(nums1[i], nums2[i]);

      result += max - min;

      if (nums2[nums2.length - 1] < min) {
        minDiff = Math.min(minDiff, min - nums2[nums2.length - 1]);
      } else if (nums2[nums2.length - 1] > max) {
        minDiff = Math.min(minDiff, nums2[nums2.length - 1] - max);
      } else {
        minDiff = Math.min(minDiff, 0);
      }
    }
    result += minDiff;

    return result;
  }
}