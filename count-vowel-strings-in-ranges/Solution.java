import java.util.Arrays;

class Solution {
  public int[] vowelStrings(String[] words, int[][] queries) {
    int[] prefixSums = new int[words.length + 1];
    for (int i = 1; i < prefixSums.length; ++i) {
      prefixSums[i] = prefixSums[i - 1] + (isVowelSided(words[i - 1]) ? 1 : 0);
    }

    return Arrays.stream(queries)
        .mapToInt(query -> prefixSums[query[1] + 1] - prefixSums[query[0]])
        .toArray();
  }

  boolean isVowelSided(String word) {
    return isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1));
  }

  boolean isVowel(char c) {
    return "aeiou".indexOf(c) >= 0;
  }
}
