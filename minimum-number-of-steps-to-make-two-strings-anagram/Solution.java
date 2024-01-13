import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int minSteps(String s, String t) {
    int[] sCounts = buildCounts(s);
    int[] tCounts = buildCounts(t);

    return IntStream.range(0, sCounts.length).map(i -> Math.abs(sCounts[i] - tCounts[i])).sum() / 2;
  }

  int[] buildCounts(String str) {
    int[] counts = new int[ALPHABET_SIZE];
    for (char c : str.toCharArray()) {
      ++counts[c - 'a'];
    }

    return counts;
  }
}