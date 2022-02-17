import java.util.stream.IntStream;

class Solution {
  public long countPairs(int[] nums1, int[] nums2) {
    int[] sortedDiffs =
        IntStream.range(0, nums1.length)
            .map(i -> nums1[i] - nums2[i])
            .boxed()
            .sorted()
            .mapToInt(x -> x)
            .toArray();

    long result = 0;
    for (int i = 0; i < nums1.length; ++i) {
      int diff = nums1[i] - nums2[i];
      result += findGreaterNum(sortedDiffs, -diff) - ((diff > 0) ? 1 : 0);
    }
    result /= 2;

    return result;
  }

  int findGreaterNum(int[] sortedDiffs, int target) {
    int result = 0;
    int lower = 0;
    int upper = sortedDiffs.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (sortedDiffs[middle] > target) {
        result = sortedDiffs.length - middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    return result;
  }
}