import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int minimumBoxes(int[] apple, int[] capacity) {
    int[] sortedCapacities =
        Arrays.stream(capacity)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int result = 0;
    int rest = Arrays.stream(apple).sum();
    while (rest > 0) {
      rest -= sortedCapacities[result];
      ++result;
    }

    return result;
  }
}