import java.util.Arrays;

class Solution {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int totalLength = nums1.length + nums2.length;

    return (totalLength % 2 == 0)
        ? ((findKthValue(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, totalLength / 2)
                + findKthValue(
                    nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, totalLength / 2 + 1))
            / 2.0)
        : findKthValue(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, totalLength / 2 + 1);
  }

  int findKthValue(
      int[] nums1,
      int beginIndex1,
      int endIndex1,
      int[] nums2,
      int beginIndex2,
      int endIndex2,
      int k) {
    if (beginIndex1 > endIndex1) {
      return nums2[beginIndex2 + (k - 1)];
    }
    if (beginIndex2 > endIndex2) {
      return nums1[beginIndex1 + (k - 1)];
    }

    int middleIndex1 = (beginIndex1 + endIndex1) / 2;
    int foundIndex2 = Arrays.binarySearch(nums2, beginIndex2, endIndex2 + 1, nums1[middleIndex1]);
    if (foundIndex2 < 0) {
      foundIndex2 = -1 - foundIndex2 - 1;
    }

    if (middleIndex1 == endIndex1 && foundIndex2 == endIndex2) {
      return (k == 1 + (endIndex2 - beginIndex2 + 1))
          ? nums1[middleIndex1]
          : nums2[beginIndex2 + (k - 1)];
    }

    return (k <= (middleIndex1 - beginIndex1 + 1) + (foundIndex2 - beginIndex2 + 1))
        ? findKthValue(nums1, beginIndex1, middleIndex1, nums2, beginIndex2, foundIndex2, k)
        : findKthValue(
            nums1,
            middleIndex1 + 1,
            endIndex1,
            nums2,
            foundIndex2 + 1,
            endIndex2,
            k - (middleIndex1 - beginIndex1 + 1) - (foundIndex2 - beginIndex2 + 1));
  }
}
