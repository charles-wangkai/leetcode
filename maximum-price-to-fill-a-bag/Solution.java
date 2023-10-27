import java.util.Arrays;

class Solution {
  public double maxPrice(int[][] items, int capacity) {
    if (Arrays.stream(items).mapToInt(item -> item[1]).sum() < capacity) {
      return -1;
    }

    Arrays.sort(
        items, (item1, item2) -> -Integer.compare(item1[0] * item2[1], item2[0] * item1[1]));

    double result = 0;
    int weightSum = 0;
    for (int i = 0; ; ++i) {
      if (weightSum + items[i][1] >= capacity) {
        result += (double) items[i][0] / items[i][1] * (capacity - weightSum);

        return result;
      }

      result += items[i][0];
      weightSum += items[i][1];
    }
  }
}
