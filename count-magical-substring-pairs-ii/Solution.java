import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public long countMagicalPairs(String s) {
    int[][] leftDistinctNumFreqs = buildDistinctNumFreqs(s);
    int[][] rightDistinctNumFreqs =
        reverse(buildDistinctNumFreqs(new StringBuilder(s).reverse().toString()));

    return IntStream.range(0, s.length() - 1)
        .mapToLong(
            i ->
                IntStream.rangeClosed(1, 26)
                    .mapToLong(
                        distinctNum ->
                            (long) leftDistinctNumFreqs[i][distinctNum]
                                * rightDistinctNumFreqs[i + 1][distinctNum])
                    .sum())
        .sum();
  }

  int[][] reverse(int[][] a) {
    return IntStream.range(0, a.length).mapToObj(i -> a[a.length - 1 - i]).toArray(int[][]::new);
  }

  int[][] buildDistinctNumFreqs(String str) {
    int[][] distinctNumFreqs = new int[str.length()][27];
    Map<Character, Integer> letterToLastIndex = new HashMap<>();
    for (int i = 0; i < str.length(); ++i) {
      letterToLastIndex.put(str.charAt(i), i);

      int[] sortedIndices =
          IntStream.concat(
                  IntStream.of(-1), letterToLastIndex.values().stream().mapToInt(Integer::intValue))
              .boxed()
              .sorted(Comparator.reverseOrder())
              .mapToInt(Integer::intValue)
              .toArray();
      for (int j = 0; j < sortedIndices.length - 1; ++j) {
        distinctNumFreqs[i][j + 1] = sortedIndices[j + 1] - sortedIndices[j];
      }
    }

    return distinctNumFreqs;
  }
}