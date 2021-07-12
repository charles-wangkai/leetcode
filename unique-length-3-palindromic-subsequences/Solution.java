class Solution {
  static final int ALPHABET_SIZE = 26;

  public int countPalindromicSubsequence(String s) {
    int[] leftCounts = new int[ALPHABET_SIZE];
    int[] rightCounts = new int[ALPHABET_SIZE];
    for (int i = 0; i < s.length(); ++i) {
      ++rightCounts[s.charAt(i) - 'a'];
    }

    boolean[][] palindromes = new boolean[ALPHABET_SIZE][ALPHABET_SIZE];
    for (int i = 0; i < s.length(); ++i) {
      if (i != 0) {
        ++leftCounts[s.charAt(i - 1) - 'a'];
      }
      --rightCounts[s.charAt(i) - 'a'];

      for (int j = 0; j < ALPHABET_SIZE; ++j) {
        if (leftCounts[j] != 0 && rightCounts[j] != 0) {
          palindromes[j][s.charAt(i) - 'a'] = true;
        }
      }
    }

    int result = 0;
    for (int i = 0; i < palindromes.length; ++i) {
      for (int j = 0; j < palindromes[i].length; ++j) {
        if (palindromes[i][j]) {
          ++result;
        }
      }
    }

    return result;
  }
}
