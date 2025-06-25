class Solution {
  static final long INF = 10_000_000_005L;

  public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
    long result = -1;
    long lower = -INF;
    long upper = INF;
    while (lower <= upper) {
      long middle = (lower + upper) / 2;
      if (computeLessEqualNum(nums1, nums2, middle) >= k) {
        result = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }

  long computeLessEqualNum(int[] nums1, int[] nums2, long target) {
    long result = 0;
    for (int num1 : nums1) {
      if (num1 < 0) {
        int index = nums2.length;
        int lowerIndex = 0;
        int upperIndex = nums2.length - 1;
        while (lowerIndex <= upperIndex) {
          int middleIndex = (lowerIndex + upperIndex) / 2;
          if ((long) num1 * nums2[middleIndex] <= target) {
            index = middleIndex;
            upperIndex = middleIndex - 1;
          } else {
            lowerIndex = middleIndex + 1;
          }
        }

        result += nums2.length - index;
      } else {
        int index = -1;
        int lowerIndex = 0;
        int upperIndex = nums2.length - 1;
        while (lowerIndex <= upperIndex) {
          int middleIndex = (lowerIndex + upperIndex) / 2;
          if ((long) num1 * nums2[middleIndex] <= target) {
            index = middleIndex;
            lowerIndex = middleIndex + 1;
          } else {
            upperIndex = middleIndex - 1;
          }
        }

        result += index + 1;
      }
    }

    return result;
  }
}
