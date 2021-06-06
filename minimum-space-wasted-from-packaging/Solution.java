import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minWastedSpace(int[] packages, int[][] boxes) {
    int n = packages.length;

    Arrays.sort(packages);
    for (int[] b : boxes) {
      Arrays.sort(b);
    }

    int[] candidates =
        IntStream.range(0, boxes.length)
            .filter(i -> boxes[i][boxes[i].length - 1] >= packages[n - 1])
            .toArray();
    if (candidates.length == 0) {
      return -1;
    }

    long[] prefixSums = new long[n];
    for (int i = 0; i < prefixSums.length; ++i) {
      prefixSums[i] = ((i == 0) ? 0 : prefixSums[i - 1]) + packages[i];
    }

    long result = Long.MAX_VALUE;
    for (int candidate : candidates) {
      int[] b = boxes[candidate];

      long waste = (long) b[b.length - 1] * n - prefixSums[n - 1];

      for (int i = b.length - 2; i >= 0; --i) {
        int index = findLessEqualIndex(packages, b[i]);
        if (index != -1) {
          waste -= (index + 1L) * (b[i + 1] - b[i]);
        }
      }

      result = Math.min(result, waste);
    }

    return (int) (result % 1_000_000_007);
  }

  int findLessEqualIndex(int[] packages, int boxSize) {
    int result = -1;
    int lower = 0;
    int upper = packages.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (packages[middle] <= boxSize) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
