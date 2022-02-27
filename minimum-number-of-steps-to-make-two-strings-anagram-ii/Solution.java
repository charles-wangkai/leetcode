import java.util.stream.IntStream;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public int minSteps(String s, String t) {
    int[] sCounts = buildCounts(s);
    int[] tCounts = buildCounts(t);

    return IntStream.range(0, ALPHABET_SIZE).map(i -> Math.abs(sCounts[i] - tCounts[i])).sum();
  }

  int[] buildCounts(String a) {
    int[] counts = new int[ALPHABET_SIZE];
    for (char c : a.toCharArray()) {
      ++counts[c - 'a'];
    }

    return counts;
  }
}