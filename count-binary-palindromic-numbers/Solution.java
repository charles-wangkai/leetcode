import java.util.stream.IntStream;

class Solution {
  public int countBinaryPalindromes(long n) {
    if (n == 0) {
      return 1;
    }

    String s = Long.toBinaryString(n);
    int result = 1 + IntStream.range(1, s.length()).map(length -> 1 << ((length - 1) / 2)).sum();

    char[] bits = new char[s.length()];
    for (int i = 0, j = bits.length - 1; i <= j; ++i, --j) {
      if (i != 0 && s.charAt(i) == '1') {
        result += 1 << ((j - i) / 2);
      }

      bits[i] = s.charAt(i);
      bits[j] = bits[i];
    }

    if (String.valueOf(bits).compareTo(s) <= 0) {
      ++result;
    }

    return result;
  }
}