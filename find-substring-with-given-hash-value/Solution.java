import java.util.stream.IntStream;

class Solution {
  public String subStrHash(String s, int power, int modulo, int k, int hashValue) {
    int beginIndex = -1;
    int current =
        IntStream.range(0, k - 1)
            .map(
                i ->
                    multiplyMod(
                        s.charAt(s.length() - k + 1 + i) - 'a' + 1,
                        powMod(power, i, modulo),
                        modulo))
            .reduce(0, (x, y) -> addMod(x, y, modulo));
    for (int i = s.length() - k; i >= 0; --i) {
      current = addMod(s.charAt(i) - 'a' + 1, multiplyMod(current, power, modulo), modulo);
      if (current == hashValue) {
        beginIndex = i;
      }

      current =
          addMod(
              current,
              -multiplyMod(s.charAt(i + k - 1) - 'a' + 1, powMod(power, k - 1, modulo), modulo),
              modulo);
    }

    return s.substring(beginIndex, beginIndex + k);
  }

  static int addMod(int x, int y, int modulo) {
    return Math.floorMod(x + y, modulo);
  }

  static int multiplyMod(int x, int y, int modulo) {
    return (int) ((long) x * y % modulo);
  }

  static int powMod(int base, int exponent, int modulo) {
    int result = 1;
    while (exponent != 0) {
      if (exponent % 2 != 0) {
        result = multiplyMod(result, base, modulo);
      }

      exponent /= 2;
      base = multiplyMod(base, base, modulo);
    }

    return result;
  }
}