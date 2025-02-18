import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public long maxWeight(int[] pizzas) {
    int[] sorted =
        Arrays.stream(pizzas)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    long result = 0;
    int index = -1;
    int oddNum = (pizzas.length / 4 + 1) / 2;
    int evenNum = pizzas.length / 4 - oddNum;
    for (int i = 0; i < oddNum; ++i) {
      ++index;
      result += sorted[index];
    }
    for (int i = 0; i < evenNum; ++i) {
      index += 2;
      result += sorted[index];
    }

    return result;
  }
}