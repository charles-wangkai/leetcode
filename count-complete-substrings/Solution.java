import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int countCompleteSubstrings(String word, int k) {
    return IntStream.rangeClosed(1, ALPHABET_SIZE)
        .map(i -> computeCompleteNum(word, k, i * k))
        .sum();
  }

  int computeCompleteNum(String word, int k, int length) {
    if (length > word.length()) {
      return 0;
    }
    if (length == 1) {
      return word.length();
    }

    int[] counts = new int[ALPHABET_SIZE];
    int invalidAdjCount = 0;
    for (int i = 0; i < length - 1; ++i) {
      ++counts[word.charAt(i) - 'a'];
      if (i != 0 && Math.abs(word.charAt(i) - word.charAt(i - 1)) > 2) {
        ++invalidAdjCount;
      }
    }

    int result = 0;
    for (int i = length - 1; i < word.length(); ++i) {
      ++counts[word.charAt(i) - 'a'];
      if (Math.abs(word.charAt(i) - word.charAt(i - 1)) > 2) {
        ++invalidAdjCount;
      }

      if (invalidAdjCount == 0
          && Arrays.stream(counts).allMatch(count -> count == 0 || count == k)) {
        ++result;
      }

      --counts[word.charAt(i - length + 1) - 'a'];
      if (Math.abs(word.charAt(i - length + 1) - word.charAt(i - length + 2)) > 2) {
        --invalidAdjCount;
      }
    }

    return result;
  }
}