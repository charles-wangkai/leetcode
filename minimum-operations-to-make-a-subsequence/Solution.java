import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minOperations(int[] target, int[] arr) {
    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, target.length).boxed().collect(Collectors.toMap(i -> target[i], i -> i));

    return target.length
        - computeLongestIncreasingSubsequenceLength(
            Arrays.stream(arr).filter(valueToIndex::containsKey).map(valueToIndex::get).toArray());
  }

  int computeLongestIncreasingSubsequenceLength(int[] a) {
    List<Integer> tails = new ArrayList<>();
    for (int x : a) {
      int index = Collections.binarySearch(tails, x);
      if (index < 0) {
        index = -1 - index;
      }

      if (index == tails.size()) {
        tails.add(x);
      } else {
        tails.set(index, x);
      }
    }

    return tails.size();
  }
}
