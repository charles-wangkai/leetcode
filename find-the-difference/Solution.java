import java.util.stream.IntStream;

class Solution {
  public char findTheDifference(String s, String t) {
    int[] sCounts = buildCounts(s);
    int[] tCounts = buildCounts(t);

    return (char)
        (IntStream.range(0, sCounts.length)
                .filter(i -> sCounts[i] != tCounts[i])
                .findAny()
                .getAsInt()
            + 'a');
  }

  int[] buildCounts(String x) {
    int[] result = new int[26];
    for (char c : x.toCharArray()) {
      ++result[c - 'a'];
    }

    return result;
  }
}
