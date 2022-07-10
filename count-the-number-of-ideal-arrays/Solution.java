import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int idealArrays(int n, int maxValue) {
    int result = 0;
    Map<Integer, Integer> valueToCount =
        IntStream.rangeClosed(1, maxValue).boxed().collect(Collectors.toMap(i -> i, i -> 1));
    for (int length = 1; length <= n && !valueToCount.isEmpty(); ++length) {
      result =
          addMod(
              result,
              multiplyMod(
                  valueToCount.values().stream().reduce(this::addMod).get(),
                  CMod(n - 1, length - 1)));

      Map<Integer, Integer> nextValueToCount = new HashMap<>();
      for (int value : valueToCount.keySet()) {
        for (int i = value * 2; i <= maxValue; i += value) {
          nextValueToCount.put(
              i, addMod(nextValueToCount.getOrDefault(i, 0), valueToCount.get(value)));
        }
      }

      valueToCount = nextValueToCount;
    }

    return result;
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int inverseMod(int x) {
    return BigInteger.valueOf(x).modInverse(BigInteger.valueOf(MODULUS)).intValue();
  }

  int CMod(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = multiplyMod(result, multiplyMod(n - i, inverseMod(i + 1)));
    }

    return result;
  }
}