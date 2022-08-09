import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int numFactoredBinaryTrees(int[] arr) {
    Arrays.sort(arr);

    Map<Integer, Integer> valueToIndex =
        IntStream.range(0, arr.length).boxed().collect(Collectors.toMap(i -> arr[i], i -> i));

    int[] dp = new int[arr.length];
    Arrays.fill(dp, 1);

    for (int i = 0; i < arr.length; ++i) {
      for (int j = 0; j < i; ++j) {
        if (arr[i] % arr[j] == 0) {
          int other = arr[i] / arr[j];
          if (valueToIndex.containsKey(other)) {
            dp[i] = addMod(dp[i], multiplyMod(dp[j], dp[valueToIndex.get(other)]));
          }
        }
      }
    }

    return Arrays.stream(dp).reduce(this::addMod).getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
