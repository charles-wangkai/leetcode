import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int minimumLines(int[][] stockPrices) {
    if (stockPrices.length == 1) {
      return 0;
    }

    Arrays.sort(stockPrices, Comparator.comparing(stockPrice -> stockPrice[0]));

    return 1
        + (int)
            IntStream.range(2, stockPrices.length)
                .filter(i -> !isSameLine(stockPrices[i - 2], stockPrices[i - 1], stockPrices[i]))
                .count();
  }

  boolean isSameLine(int[] p1, int[] p2, int[] p3) {
    return (long) (p3[1] - p1[1]) * (p2[0] - p1[0]) == (long) (p2[1] - p1[1]) * (p3[0] - p1[0]);
  }
}