import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public String answerString(String word, int numFriends) {
    if (numFriends == 1) {
      return word;
    }

    int[] suffixArray = buildSuffixArray(word);
    String suffix = word.substring(suffixArray[suffixArray.length - 1]);

    return suffix.substring(0, Math.min(word.length() - numFriends + 1, suffix.length()));
  }

  int[] buildSuffixArray(String s) {
    int n = s.length();

    Integer[] suffixArray = new Integer[n + 1];
    final int[] ranks = new int[n + 1];
    for (int i = 0; i <= n; ++i) {
      suffixArray[i] = i;
      ranks[i] = (i == n) ? -1 : s.charAt(i);
    }

    for (int k = 1; k <= n; k *= 2) {
      final int k_ = k;
      Comparator<Integer> comparator =
          (i1, i2) -> {
            if (ranks[i1] != ranks[i2]) {
              return ranks[i1] - ranks[i2];
            }

            return ((i1 + k_ < ranks.length) ? ranks[i1 + k_] : -1)
                - ((i2 + k_ < ranks.length) ? ranks[i2 + k_] : -1);
          };

      Arrays.sort(suffixArray, comparator);

      int[] nextRanks = new int[ranks.length];
      for (int i = 1; i <= n; ++i) {
        nextRanks[suffixArray[i]] =
            nextRanks[suffixArray[i - 1]]
                + ((comparator.compare(suffixArray[i - 1], suffixArray[i]) == 0) ? 0 : 1);
      }

      for (int i = 0; i < ranks.length; ++i) {
        ranks[i] = nextRanks[i];
      }
    }

    int[] result = new int[suffixArray.length];
    for (int i = 0; i < result.length; ++i) {
      result[i] = suffixArray[i];
    }

    return result;
  }
}
