import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int longestStrChain(String[] words) {
    Arrays.sort(words, Comparator.comparing(String::length));

    int[] lengths = new int[words.length];
    for (int i = 0; i < lengths.length; ++i) {
      lengths[i] = 1;

      for (int j = 0; j < i; ++j) {
        if (isPredecessor(words[j], words[i])) {
          lengths[i] = Math.max(lengths[i], lengths[j] + 1);
        }
      }
    }

    return Arrays.stream(lengths).max().getAsInt();
  }

  boolean isPredecessor(String word1, String word2) {
    if (word1.length() + 1 != word2.length()) {
      return false;
    }

    boolean inserted = false;
    int index1 = 0;
    for (int index2 = 0; index1 < word1.length() && index2 < word2.length(); ++index2) {
      if (word1.charAt(index1) == word2.charAt(index2)) {
        ++index1;
      } else {
        if (inserted) {
          return false;
        }
        inserted = true;
      }
    }

    return true;
  }
}
