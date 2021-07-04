import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
  static final int MODULUS1 = 1_000_000_007;
  static final int MODULUS2 = 1_000_000_009;

  public int longestCommonSubpath(int n, int[][] paths) {
    int result = 0;
    int lower = 1;
    int upper = Arrays.stream(paths).mapToInt(path -> path.length).min().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (check(n, paths, middle)) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  boolean check(int n, int[][] paths, int length) {
    int placeValue1 = powMod(n, length - 1, MODULUS1);
    int placeValue2 = powMod(n, length - 1, MODULUS2);

    Map<Long, Integer> codesToCount = new HashMap<>();
    for (int[] path : paths) {
      Set<Long> codes = new HashSet<>();

      int code1 = 0;
      int code2 = 0;
      for (int i = 0; i < length - 1; ++i) {
        code1 = addMod(multiplyMod(code1, n, MODULUS1), path[i], MODULUS1);
        code2 = addMod(multiplyMod(code2, n, MODULUS2), path[i], MODULUS2);
      }

      for (int i = length - 1; i < path.length; ++i) {
        code1 = addMod(multiplyMod(code1, n, MODULUS1), path[i], MODULUS1);
        code2 = addMod(multiplyMod(code2, n, MODULUS2), path[i], MODULUS2);
        codes.add((long) code1 * code2);

        code1 =
            subtractMod(code1, multiplyMod(path[i - length + 1], placeValue1, MODULUS1), MODULUS1);
        code2 =
            subtractMod(code2, multiplyMod(path[i - length + 1], placeValue2, MODULUS2), MODULUS2);
      }

      for (long c : codes) {
        codesToCount.put(c, codesToCount.getOrDefault(c, 0) + 1);
      }
    }

    return codesToCount.values().stream().anyMatch(count -> count == paths.length);
  }

  int addMod(int x, int y, int m) {
    return (x + y) % m;
  }

  int subtractMod(int x, int y, int m) {
    return (x - y + m) % m;
  }

  int multiplyMod(int x, int y, int m) {
    return (int) ((long) x * y % m);
  }

  int powMod(int base, long exponent, int m) {
    int result = 1;
    while (exponent != 0) {
      if ((exponent & 1) != 0) {
        result = multiplyMod(result, base, m);
      }

      base = multiplyMod(base, base, m);
      exponent >>= 1;
    }

    return result;
  }
}
