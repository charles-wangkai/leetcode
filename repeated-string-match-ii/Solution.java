import java.util.stream.IntStream;

class Solution {
  public boolean repeatedStringMatch(String a, String b, int x, int y) {
    return IntStream.range(0, a.length()).anyMatch(aIndex -> check(a, b, x, y, aIndex));
  }

  boolean check(String a, String b, int x, int y, int aIndex) {
    if ((long) b.length() * y > (long) a.length() * x - aIndex) {
      return false;
    }

    boolean[][] seen = new boolean[a.length()][b.length()];
    int bIndex = 0;
    for (int i = 0; i < (long) b.length() * y; ++i) {
      if (a.charAt(aIndex) != b.charAt(bIndex)) {
        return false;
      }

      if (seen[aIndex][bIndex]) {
        return true;
      }
      seen[aIndex][bIndex] = true;

      aIndex = (aIndex + 1) % a.length();
      bIndex = (bIndex + 1) % b.length();
    }

    return true;
  }
}