import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int numberOfWeakCharacters(int[][] properties) {
    Arrays.sort(
        properties, Comparator.comparing((int[] p) -> p[0]).reversed().thenComparing(p -> p[1]));

    int result = 0;
    int maxDefense = -1;
    for (int[] property : properties) {
      if (property[1] < maxDefense) {
        ++result;
      } else {
        maxDefense = property[1];
      }
    }

    return result;
  }
}
