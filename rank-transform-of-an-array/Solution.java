import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] arrayRankTransform(int[] arr) {
    int[] sortedValue = Arrays.stream(arr).sorted().distinct().toArray();
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, sortedValue.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedValue[i], i -> i));

    return Arrays.stream(arr).map(value -> valueToIndex.get(value) + 1).toArray();
  }
}
