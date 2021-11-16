import java.util.Arrays;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;

class Solution {
  public int[] maximumBeauty(int[][] items, int[] queries) {
    Arrays.sort(items, Comparator.comparing(item -> item[0]));

    NavigableMap<Integer, Integer> priceToMaxBeauty = new TreeMap<>();
    int maxBeauty = Integer.MIN_VALUE;
    for (int[] item : items) {
      maxBeauty = Math.max(maxBeauty, item[1]);
      priceToMaxBeauty.put(item[0], maxBeauty);
    }

    return Arrays.stream(queries)
        .map(
            query -> {
              Entry<Integer, Integer> entry = priceToMaxBeauty.floorEntry(query);

              return (entry == null) ? 0 : entry.getValue();
            })
        .toArray();
  }
}
