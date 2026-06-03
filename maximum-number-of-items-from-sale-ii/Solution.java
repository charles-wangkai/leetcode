import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int maximumSaleItems(int[][] items, int budget) {
    int[] freeCounts = buildFreeCounts(items);

    int minPrice = Arrays.stream(items).mapToInt(item -> item[1]).min().getAsInt();

    Element[] elements =
        IntStream.range(0, items.length)
            .filter(i -> items[i][1] < minPrice * 2)
            .mapToObj(i -> new Element(items[i][1], freeCounts[i]))
            .sorted(Comparator.comparing(Element::price))
            .toArray(Element[]::new);

    int result = 0;
    for (Element element : elements) {
      int count = Math.min(element.count(), budget / element.price());
      result += 2 * count;

      budget -= element.price() * count;
    }

    result += budget / minPrice;

    return result;
  }

  int[] buildFreeCounts(int[][] items) {
    Map<Integer, Integer> factorToCount = new HashMap<>();
    for (int[] item : items) {
      factorToCount.put(item[0], factorToCount.getOrDefault(item[0], 0) + 1);
    }

    int[] factors = Arrays.stream(items).mapToInt(item -> item[0]).distinct().sorted().toArray();
    Map<Integer, Integer> factorToFreeCount = new HashMap<>();
    for (int factor : factors) {
      int freeCount = -1;
      for (int i = factor; i <= factors[factors.length - 1]; i += factor) {
        freeCount += factorToCount.getOrDefault(i, 0);
      }

      factorToFreeCount.put(factor, freeCount);
    }

    return Arrays.stream(items).mapToInt(item -> factorToFreeCount.get(item[0])).toArray();
  }
}

record Element(int price, int count) {}
