import java.util.stream.IntStream;

class Solution {
  public boolean canMakeSubsequence(String s, String t) {
    if (s.length() == 1) {
      return true;
    }

    int[] leftIndices = buildLeftIndices(s, t);
    int[] rightIndices = buildRightIndices(s, t);

    return s.length() <= t.length()
        && IntStream.range(0, s.length())
            .anyMatch(
                i ->
                    (i == 0 && rightIndices[i + 1] != -1 && rightIndices[i + 1] >= 1)
                        || (i == s.length() - 1
                            && leftIndices[i - 1] != -1
                            && leftIndices[i - 1] <= t.length() - 2)
                        || (i != 0
                            && i != s.length() - 1
                            && leftIndices[i - 1] != -1
                            && rightIndices[i + 1] != -1
                            && leftIndices[i - 1] + 1 < rightIndices[i + 1]));
  }

  int[] buildLeftIndices(String s, String t) {
    int[] result = new int[s.length()];
    int index = 0;
    for (int i = 0; i < result.length; ++i) {
      while (index != t.length() && t.charAt(index) != s.charAt(i)) {
        ++index;
      }

      if (index == t.length()) {
        result[i] = -1;
      } else {
        result[i] = index;
        ++index;
      }
    }

    return result;
  }

  int[] buildRightIndices(String s, String t) {
    int[] result = new int[s.length()];
    int index = t.length() - 1;
    for (int i = result.length - 1; i >= 0; --i) {
      while (index != -1 && t.charAt(index) != s.charAt(i)) {
        --index;
      }

      if (index == -1) {
        result[i] = -1;
      } else {
        result[i] = index;
        --index;
      }
    }

    return result;
  }
}