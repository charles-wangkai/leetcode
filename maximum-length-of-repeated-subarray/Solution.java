class Solution {
  public int findLength(int[] nums1, int[] nums2) {
    int result = 0;
    for (int beginIndex1 = 0; beginIndex1 < nums1.length; ++beginIndex1) {
      for (int beginIndex2 = 0; beginIndex2 < nums2.length; ++beginIndex2) {
        int length = 0;
        for (int i = beginIndex1, j = beginIndex2;
            i < nums1.length && j < nums2.length && nums1[i] == nums2[j];
            ++i, ++j) {
          ++length;
        }

        result = Math.max(result, length);
      }
    }

    return result;
  }
}
