// https://leetcode.com/problems/threshold-majority-queries/solutions/7036983/bruteforce/

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] subarrayMajority(int[] nums, int[][] queries) {
    int[] sorted = Arrays.stream(nums).sorted().distinct().toArray();
    Map<Integer, Integer> valueToCompressed =
        IntStream.range(0, sorted.length).boxed().collect(Collectors.toMap(i -> sorted[i], i -> i));

    int[][] counts = new int[nums.length + 1][sorted.length];
    for (int i = 1; i < counts.length; ++i) {
      for (int j = 0; j < sorted.length; ++j) {
        counts[i][j] = counts[i - 1][j] + ((valueToCompressed.get(nums[i - 1]) == j) ? 1 : 0);
      }
    }

    int[] result = new int[queries.length];
    Arrays.fill(result, -1);

    for (int i = 0; i < result.length; ++i) {
      int maxFreq = queries[i][2] - 1;
      for (int j = 0; j < sorted.length; ++j) {
        int freq = counts[queries[i][1] + 1][j] - counts[queries[i][0]][j];
        if (freq > maxFreq) {
          maxFreq = freq;
          result[i] = sorted[j];
        }
      }
    }

    return result;
  }
}
