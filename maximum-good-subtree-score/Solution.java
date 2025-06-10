import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  static final int MODULUS = 1_000_000_007;

  int maxScoreSum;

  public int goodSubtreeSum(int[] vals, int[] par) {
    int n = vals.length;

    @SuppressWarnings("unchecked")
    List<Integer>[] childLists = new List[n];
    for (int i = 0; i < childLists.length; ++i) {
      childLists[i] = new ArrayList<>();
    }
    for (int i = 1; i < par.length; ++i) {
      childLists[par[i]].add(i);
    }

    int[] masks = Arrays.stream(vals).map(this::computeMask).toArray();

    maxScoreSum = 0;
    search(vals, masks, childLists, 0);

    return maxScoreSum;
  }

  int computeMask(int x) {
    int[] counts = new int[10];
    while (x != 0) {
      ++counts[x % 10];
      x /= 10;
    }

    int result = 0;
    for (int i = 0; i < counts.length; ++i) {
      if (counts[i] > 1) {
        return -1;
      }
      if (counts[i] == 1) {
        result |= 1 << i;
      }
    }

    return result;
  }

  long[] search(int[] vals, int[] masks, List<Integer>[] childLists, int node) {
    long[] maxSums = new long[1 << 10];
    if (masks[node] != -1) {
      maxSums[masks[node]] = vals[node];
    }
    for (int child : childLists[node]) {
      long[] subMaxSums = search(vals, masks, childLists, child);
      maxSums = merge(maxSums, subMaxSums);
    }

    maxScoreSum = addMod(maxScoreSum, (int) (Arrays.stream(maxSums).max().getAsLong() % MODULUS));

    return maxSums;
  }

  long[] merge(long[] maxSums1, long[] maxSums2) {
    long[] result = new long[1 << 10];
    for (int i = 0; i < result.length; ++i) {
      result[i] = Math.max(maxSums1[i], maxSums2[i]);
    }

    for (int i = 0; i < maxSums1.length; ++i) {
      if (maxSums1[i] != 0) {
        for (int j = 0; j < maxSums2.length; ++j) {
          if ((i & j) == 0) {
            result[i | j] = Math.max(result[i | j], maxSums1[i] + maxSums2[j]);
          }
        }
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }
}