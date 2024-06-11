import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public int[] relativeSortArray(int[] arr1, int[] arr2) {
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, arr2.length).boxed().collect(Collectors.toMap(i -> arr2[i], i -> i));

    return Arrays.stream(arr1)
        .boxed()
        .sorted(
            Comparator.<Integer, Integer>comparing(
                    i -> valueToIndex.getOrDefault(i, Integer.MAX_VALUE))
                .thenComparing(i -> i))
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
