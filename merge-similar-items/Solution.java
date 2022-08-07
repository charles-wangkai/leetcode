import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Solution {
  public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
    Map<Integer, Integer> valueToWeight = new HashMap<>();
    for (int[] item :
        Stream.concat(Arrays.stream(items1), Arrays.stream(items2)).toArray(int[][]::new)) {
      valueToWeight.put(item[0], valueToWeight.getOrDefault(item[0], 0) + item[1]);
    }

    return valueToWeight.keySet().stream()
        .sorted()
        .map(value -> List.of(value, valueToWeight.get(value)))
        .toList();
  }
}