import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public int maxUniqueSplit(String s) {
    return IntStream.range(0, 1 << (s.length() - 1))
            .filter(mask -> check(s, mask))
            .map(Integer::bitCount)
            .max()
            .getAsInt()
        + 1;
  }

  boolean check(String s, int mask) {
    Set<String> seen = new HashSet<>();
    StringBuilder part = new StringBuilder();
    for (int i = 0; i < s.length(); ++i) {
      part.append(s.charAt(i));
      if (i == s.length() - 1 || ((mask >> i) & 1) == 1) {
        if (seen.contains(part.toString())) {
          return false;
        }

        seen.add(part.toString());
        part = new StringBuilder();
      }
    }

    return true;
  }
}