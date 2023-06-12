import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
    Map<Integer, Integer> valueToIndex = new HashMap<>();
    for (int i = 0; i < grid.length; ++i) {
      valueToIndex.put(
          Integer.parseInt(
              Arrays.stream(grid[i]).mapToObj(String::valueOf).collect(Collectors.joining()), 2),
          i);
    }

    if (valueToIndex.containsKey(0)) {
      return List.of(valueToIndex.get(0));
    }

    for (int value1 : valueToIndex.keySet()) {
      for (int value2 : valueToIndex.keySet()) {
        if ((value1 & value2) == 0) {
          return IntStream.of(valueToIndex.get(value1), valueToIndex.get(value2))
              .sorted()
              .boxed()
              .toList();
        }
      }
    }

    return List.of();
  }
}
