import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int countAnagrams(String s) {
    return Arrays.stream(s.split(" "))
        .mapToInt(
            word -> {
              Map<Character, Integer> letterToCount = new HashMap<>();
              for (char letter : word.toCharArray()) {
                letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
              }

              int result = 1;
              int rest = word.length();
              for (int count : letterToCount.values()) {
                result = multiplyMod(result, CMod(rest, count));
                rest -= count;
              }

              return result;
            })
        .reduce(this::multiplyMod)
        .getAsInt();
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }

  int divideMod(int x, int y) {
    return multiplyMod(x, BigInteger.valueOf(y).modInverse(BigInteger.valueOf(MODULUS)).intValue());
  }

  int CMod(int n, int r) {
    return divideMod(factorialMod(n), multiplyMod(factorialMod(r), factorialMod(n - r)));
  }

  int factorialMod(int x) {
    return IntStream.rangeClosed(1, x).reduce(1, this::multiplyMod);
  }
}
