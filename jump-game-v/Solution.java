import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  static final int[] DIRECTIONS = {-1, 1};

  public int maxJumps(int[] arr, int d) {
    int[] indices =
        IntStream.range(0, arr.length)
            .boxed()
            .sorted(Comparator.comparing(i -> arr[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    int[] dp = new int[arr.length];
    Arrays.fill(dp, 1);

    for (int index : indices) {
      for (int direction : DIRECTIONS) {
        int maxValue = arr[index];
        for (int i = 1; i <= d; ++i) {
          int nextIndex = index + direction * i;
          if (nextIndex < 0 || nextIndex >= arr.length) {
            break;
          }

          if (arr[nextIndex] > maxValue) {
            dp[nextIndex] = Math.max(dp[nextIndex], dp[index] + 1);
            maxValue = arr[nextIndex];
          }
        }
      }
    }

    return Arrays.stream(dp).max().getAsInt();
  }
}
