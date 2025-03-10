import java.util.OptionalInt;
import java.util.stream.IntStream;

class Solution {
  public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
    int result = 0;
    boolean[] used = new boolean[baskets.length];
    for (int fruit : fruits) {
      OptionalInt index =
          IntStream.range(0, baskets.length)
              .filter(i -> !used[i] && baskets[i] >= fruit)
              .findFirst();
      if (index.isPresent()) {
        used[index.getAsInt()] = true;
      } else {
        ++result;
      }
    }

    return result;
  }
}