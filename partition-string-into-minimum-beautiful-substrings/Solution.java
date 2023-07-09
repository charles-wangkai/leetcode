import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  static final Set<String> BEAUTIFULS =
      IntStream.of(1, 5, 5 * 5, 5 * 5 * 5, 5 * 5 * 5 * 5, 5 * 5 * 5 * 5 * 5, 5 * 5 * 5 * 5 * 5 * 5)
          .mapToObj(Integer::toBinaryString)
          .collect(Collectors.toSet());

  public int minimumBeautifulSubstrings(String s) {
    int result = Integer.MAX_VALUE;
    for (int mask = 1; mask < 1 << s.length(); mask += 2) {
      int mask_ = mask;
      int[] beginIndices =
          IntStream.range(0, s.length()).filter(i -> ((mask_ >> i) & 1) == 1).toArray();
      if (check(s, beginIndices)) {
        result = Math.min(result, beginIndices.length);
      }
    }

    return (result == Integer.MAX_VALUE) ? -1 : result;
  }

  boolean check(String s, int[] beginIndices) {
    for (int beginIndex : beginIndices) {
      if (s.charAt(beginIndex) == '0') {
        return false;
      }
    }
    for (int i = 0; i < beginIndices.length; ++i) {
      String part =
          s.substring(
              beginIndices[i], (i == beginIndices.length - 1) ? s.length() : beginIndices[i + 1]);
      if (!BEAUTIFULS.contains(part)) {
        return false;
      }
    }

    return true;
  }
}
