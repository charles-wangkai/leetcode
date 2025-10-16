// https://en.wikipedia.org/wiki/Erd%C5%91s%E2%80%93Gallai_theorem

import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public boolean simpleGraphExists(int[] degrees) {
    int n = degrees.length;

    int[] sorted =
        Arrays.stream(degrees)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    if (Arrays.stream(sorted).asLongStream().sum() % 2 == 1) {
      return false;
    }

    long prefixSum = 0;
    long suffixSum = 0;
    int suffixSize = 0;
    for (int k = 1; k <= n; ++k) {
      prefixSum += sorted[k - 1];

      while (k + suffixSize < n && sorted[sorted.length - 1 - suffixSize] <= k) {
        suffixSum += sorted[sorted.length - 1 - suffixSize];
        ++suffixSize;
      }

      if (k + suffixSize == n + 1) {
        suffixSum -= sorted[sorted.length - suffixSize];
        --suffixSize;
      }

      if (prefixSum > k * (k - 1L) + (long) (n - k - suffixSize) * k + suffixSum) {
        return false;
      }
    }

    return true;
  }
}