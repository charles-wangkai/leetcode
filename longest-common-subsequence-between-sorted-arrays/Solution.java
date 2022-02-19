import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 100;

  public List<Integer> longestCommonSubsequence(int[][] arrays) {
    int[] counts = new int[LIMIT + 1];
    for (int[] array : arrays) {
      for (int value : array) {
        ++counts[value];
      }
    }

    return IntStream.range(0, counts.length)
        .filter(i -> counts[i] == arrays.length)
        .boxed()
        .collect(Collectors.toList());
  }
}