import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> minimumAbsDifference(int[] arr) {
    int[] sorted = Arrays.stream(arr).boxed().sorted().mapToInt(x -> x).toArray();
    int minDiff =
        IntStream.range(0, sorted.length - 1).map(i -> sorted[i + 1] - sorted[i]).min().getAsInt();

    return IntStream.range(0, sorted.length - 1)
        .filter(i -> sorted[i + 1] - sorted[i] == minDiff)
        .mapToObj(i -> List.of(sorted[i], sorted[i + 1]))
        .collect(Collectors.toList());
  }
}
