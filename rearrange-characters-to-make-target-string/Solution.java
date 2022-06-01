import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int rearrangeCharacters(String s, String target) {
    int[] sCounts = buildCounts(s);
    int[] targetCounts = buildCounts(target);

    return IntStream.range(0, sCounts.length)
        .filter(i -> targetCounts[i] != 0)
        .map(i -> sCounts[i] / targetCounts[i])
        .min()
        .getAsInt();
  }

  int[] buildCounts(String str) {
    int[] result = new int[ALPHABET_SIZE];
    for (char c : str.toCharArray()) {
      ++result[c - 'a'];
    }

    return result;
  }
}