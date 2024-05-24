import java.util.stream.IntStream;

class Solution {
  public int maxScoreWords(String[] words, char[] letters, int[] score) {
    int[] counts = new int[26];
    for (char letter : letters) {
      ++counts[letter - 'a'];
    }

    return IntStream.range(0, 1 << words.length)
        .map(mask -> computeScore(words, counts, score, mask))
        .max()
        .getAsInt();
  }

  int computeScore(String[] words, int[] counts, int[] score, int mask) {
    int result = 0;
    int[] rests = counts.clone();
    for (int i = 0; i < words.length; ++i) {
      if (((mask >> i) & 1) == 1) {
        for (char letter : words[i].toCharArray()) {
          if (rests[letter - 'a'] == 0) {
            return 0;
          }

          --rests[letter - 'a'];
          result += score[letter - 'a'];
        }
      }
    }

    return result;
  }
}
