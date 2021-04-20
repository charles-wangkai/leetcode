import java.util.Arrays;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int longestPalindromeSubseq(String s) {
    int[][][] maxLengths = new int[s.length()][s.length()][ALPHABET_SIZE];
    for (int length = 2; length <= s.length(); ++length) {
      for (int beginIndex = 0; beginIndex + length <= s.length(); ++beginIndex) {
        int endIndex = beginIndex + length - 1;
        char begin = s.charAt(beginIndex);
        char end = s.charAt(endIndex);

        for (int inner = 0; inner < ALPHABET_SIZE; ++inner) {
          maxLengths[beginIndex][endIndex][inner] =
              Math.max(
                  maxLengths[beginIndex][endIndex][inner],
                  Math.max(
                      maxLengths[beginIndex + 1][endIndex][inner],
                      maxLengths[beginIndex][endIndex - 1][inner]));
        }

        if (begin == end) {
          if (length == 2) {
            maxLengths[beginIndex][endIndex][begin - 'a'] = 2;
          } else {
            for (int inner = 0; inner < ALPHABET_SIZE; ++inner) {
              if (inner != begin - 'a') {
                maxLengths[beginIndex][endIndex][begin - 'a'] =
                    Math.max(
                        maxLengths[beginIndex][endIndex][begin - 'a'],
                        maxLengths[beginIndex + 1][endIndex - 1][inner] + 2);
              }
            }
          }
        }
      }
    }

    return Arrays.stream(maxLengths[0][s.length() - 1]).max().getAsInt();
  }
}
