// https://leetcode.com/problems/minimum-time-to-make-array-sum-at-most-x/solutions/3868184/java-c-python-dp-solution-o-n-2/

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minimumTime(List<Integer> nums1, List<Integer> nums2, int x) {
    int[] sortedIndices =
        IntStream.range(0, nums1.size())
            .boxed()
            .sorted(Comparator.comparing(nums2::get))
            .mapToInt(Integer::intValue)
            .toArray();

    int[][] dp = new int[nums1.size() + 1][nums1.size() + 1];
    for (int i = 1; i <= nums1.size(); ++i) {
      for (int j = 1; j <= i; ++j) {
        dp[i][j] =
            Math.max(
                dp[i - 1][j],
                dp[i - 1][j - 1]
                    + nums1.get(sortedIndices[i - 1])
                    + j * nums2.get(sortedIndices[i - 1]));
      }
    }

    return IntStream.rangeClosed(0, nums1.size())
        .filter(
            i ->
                nums1.stream().mapToInt(Integer::intValue).sum()
                        + nums2.stream().mapToInt(Integer::intValue).sum() * i
                        - dp[nums1.size()][i]
                    <= x)
        .findFirst()
        .orElse(-1);
  }
}
