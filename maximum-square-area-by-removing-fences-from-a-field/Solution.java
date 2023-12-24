import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
    Set<Integer> hSizes = buildSizes(m, hFences);

    long maxArea = -1;
    for (int vSize : buildSizes(n, vFences)) {
      if (hSizes.contains(vSize)) {
        maxArea = Math.max(maxArea, (long) vSize * vSize);
      }
    }

    return (maxArea == -1) ? -1 : (int) (maxArea % 1_000_000_007);
  }

  Set<Integer> buildSizes(int length, int[] fences) {
    int[] values =
        IntStream.concat(IntStream.of(1, length), Arrays.stream(fences)).sorted().toArray();

    Set<Integer> result = new HashSet<>();
    for (int i = 0; i < values.length; ++i) {
      for (int j = i + 1; j < values.length; ++j) {
        result.add(values[j] - values[i]);
      }
    }

    return result;
  }
}