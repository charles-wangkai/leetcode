import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public boolean checkAlmostEquivalent(String word1, String word2) {
    int[] counts1 = buildCounts(word1);
    int[] counts2 = buildCounts(word2);

    return IntStream.range(0, counts1.length).allMatch(i -> Math.abs(counts1[i] - counts2[i]) <= 3);
  }

  int[] buildCounts(String word) {
    int[] result = new int[ALPHABET_SIZE];
    for (char ch : word.toCharArray()) {
      ++result[ch - 'a'];
    }

    return result;
  }
}
