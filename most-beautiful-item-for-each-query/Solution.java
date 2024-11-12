import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] maximumBeauty(int[][] items, int[] queries) {
    Arrays.sort(items, Comparator.comparing(item -> item[0]));

    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] result = new int[queries.length];
    int itemIndex = -1;
    int maxBeauty = 0;
    for (int queryIndex : sortedQueryIndices) {
      while (itemIndex != items.length - 1 && items[itemIndex + 1][0] <= queries[queryIndex]) {
        ++itemIndex;
        maxBeauty = Math.max(maxBeauty, items[itemIndex][1]);
      }

      result[queryIndex] = maxBeauty;
    }

    return result;
  }
}
