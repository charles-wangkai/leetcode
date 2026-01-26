import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> minimumAbsDifference(int[] arr) {
    Arrays.sort(arr);
    int minDiff = IntStream.range(0, arr.length - 1).map(i -> arr[i + 1] - arr[i]).min().getAsInt();

    return IntStream.range(0, arr.length - 1)
        .filter(i -> arr[i + 1] - arr[i] == minDiff)
        .mapToObj(i -> List.of(arr[i], arr[i + 1]))
        .toList();
  }
}
