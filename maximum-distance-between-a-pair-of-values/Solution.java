class Solution {
  public int maxDistance(int[] nums1, int[] nums2) {
    int result = 0;
    int index2 = 0;
    for (int index1 = 0; index1 < nums1.length; ++index1) {
      while (index2 + 1 != nums2.length && nums1[index1] <= nums2[index2 + 1]) {
        ++index2;
      }

      result = Math.max(result, index2 - index1);
    }

    return result;
  }
}
