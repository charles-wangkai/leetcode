import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int numberOfWeakCharacters(int[][] properties) {
    Arrays.sort(properties, Comparator.comparing(p -> p[0]));

    int result = 0;
    int higherMax = -1;
    int currentKey = Integer.MAX_VALUE;
    int currentMax = -1;
    for (int i = properties.length - 1; i >= 0; --i) {
      if (properties[i][0] == currentKey) {
        if (properties[i][1] < higherMax) {
          ++result;
        }

        currentMax = Math.max(currentMax, properties[i][1]);
      } else {
        if (properties[i][1] < higherMax || properties[i][1] < currentMax) {
          ++result;
        }

        higherMax = Math.max(higherMax, currentMax);
        currentKey = properties[i][0];
        currentMax = properties[i][1];
      }
    }

    return result;
  }
}
