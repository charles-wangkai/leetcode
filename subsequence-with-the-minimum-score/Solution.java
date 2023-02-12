import java.util.stream.IntStream;

class Solution {
  public int minimumScore(String s, String t) {
    int[] leftLengths = new int[s.length()];
    int leftLength = 0;
    for (int i = 0; i < leftLengths.length; ++i) {
      if (leftLength != t.length() && s.charAt(i) == t.charAt(leftLength)) {
        ++leftLength;
      }

      leftLengths[i] = leftLength;
    }

    int[] rightLengths = new int[s.length()];
    int rightLength = 0;
    for (int i = rightLengths.length - 1; i >= 0; --i) {
      if (rightLength != t.length() && s.charAt(i) == t.charAt(t.length() - 1 - rightLength)) {
        ++rightLength;
      }

      rightLengths[i] = rightLength;
    }

    return IntStream.range(-1, s.length())
        .map(
            i ->
                t.length()
                    - Math.min(
                        t.length(),
                        ((i == -1) ? 0 : leftLengths[i])
                            + ((i == s.length() - 1) ? 0 : rightLengths[i + 1])))
        .min()
        .getAsInt();
  }
}
