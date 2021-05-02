import java.math.BigInteger;
import java.util.stream.IntStream;

class Solution {
  public boolean splitString(String s) {
    for (int i = 1; i <= s.length() / 2; ++i) {
      if (search(
          s.substring(0, s.length() - i), Long.parseLong(s.substring(s.length() - i)), false)) {
        return true;
      }
    }

    return false;
  }

  boolean search(String s, long prev, boolean allowZero) {
    long current = prev + 1;
    if (s.isEmpty()
        || new BigInteger(s).equals(BigInteger.valueOf(current))
        || (allowZero && s.chars().allMatch(ch -> ch == '0'))) {
      return true;
    }

    String currentS = String.valueOf(current);
    int index = s.lastIndexOf(currentS);
    if (index == -1
        || IntStream.range(index + currentS.length(), s.length())
            .anyMatch(i -> s.charAt(i) != '0')) {
      return false;
    }

    return search(s.substring(0, index), current, true);
  }
}
