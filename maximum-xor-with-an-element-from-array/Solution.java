import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  static final int BIT_NUM = 30;

  public int[] maximizeXor(int[] nums, int[][] queries) {
    nums = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i][1]))
            .mapToInt(x -> x)
            .toArray();

    int[] result = new int[queries.length];
    int numIndex = 0;
    @SuppressWarnings("unchecked")
    Set<Integer>[] prefixes = new Set[BIT_NUM];
    for (int i = 0; i < prefixes.length; ++i) {
      prefixes[i] = new HashSet<>();
    }
    for (int sortedQueryIndex : sortedQueryIndices) {
      while (numIndex != nums.length && nums[numIndex] <= queries[sortedQueryIndex][1]) {
        int prefix = 0;
        for (int i = BIT_NUM - 1; i >= 0; --i) {
          prefix += nums[numIndex] & (1 << i);
          prefixes[i].add(prefix);
        }

        ++numIndex;
      }

      int x = queries[sortedQueryIndex][0];
      int prefix = 0;
      for (int i = BIT_NUM - 1; i >= 0; --i) {
        int prefixZero = prefix;
        boolean hasPrefixZero = prefixes[i].contains(prefixZero);
        int prefixOne = prefix + (1 << i);
        boolean hasPrefixOne = prefixes[i].contains(prefixOne);

        if (!hasPrefixZero && !hasPrefixOne) {
          result[sortedQueryIndex] = -1;

          break;
        } else {
          if ((x & (1 << i)) == 0) {
            if (hasPrefixOne) {
              prefix = prefixOne;
            } else {
              prefix = prefixZero;
            }
          } else {
            if (hasPrefixZero) {
              prefix = prefixZero;
            } else {
              prefix = prefixOne;
            }
          }
        }

        if (i == 0) {
          result[sortedQueryIndex] = x ^ prefix;
        }
      }
    }

    return result;
  }
}
