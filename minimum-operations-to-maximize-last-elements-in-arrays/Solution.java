class Solution {
  public int minOperations(int[] nums1, int[] nums2) {
    int n = nums1.length;

    int operationNum =
        Math.min(
            computeOperationNum(nums1, nums2, nums1[n - 1], nums2[n - 1]),
            computeOperationNum(nums1, nums2, nums2[n - 1], nums1[n - 1]));

    return (operationNum == Integer.MAX_VALUE) ? -1 : operationNum;
  }

  int computeOperationNum(int[] nums1, int[] nums2, int max1, int max2) {
    int result = 0;
    for (int i = 0; i < nums1.length; ++i) {
      if (!(nums1[i] <= max1 && nums2[i] <= max2)) {
        if (nums2[i] <= max1 && nums1[i] <= max2) {
          ++result;
        } else {
          return Integer.MAX_VALUE;
        }
      }
    }

    return result;
  }
}
