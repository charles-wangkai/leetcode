import java.util.Arrays;

class Solution {
  public int[] platesBetweenCandles(String s, int[][] queries) {
    int[] leftPlateNums = new int[s.length()];
    for (int i = 0; i < leftPlateNums.length; ++i) {
      leftPlateNums[i] = ((i == 0) ? 0 : leftPlateNums[i - 1]) + ((s.charAt(i) == '*') ? 1 : 0);
    }

    int[] leftCandleIndices = new int[s.length()];
    int leftCandleIndex = -1;
    for (int i = 0; i < leftCandleIndices.length; ++i) {
      if (s.charAt(i) == '|') {
        leftCandleIndex = i;
      }
      leftCandleIndices[i] = leftCandleIndex;
    }

    int[] rightCandleIndices = new int[s.length()];
    int rightCandleIndex = s.length();
    for (int i = s.length() - 1; i >= 0; --i) {
      if (s.charAt(i) == '|') {
        rightCandleIndex = i;
      }
      rightCandleIndices[i] = rightCandleIndex;
    }

    return Arrays.stream(queries)
        .mapToInt(
            query ->
                (rightCandleIndices[query[0]] <= leftCandleIndices[query[1]])
                    ? (leftPlateNums[leftCandleIndices[query[1]]]
                        - ((rightCandleIndices[query[0]] == 0)
                            ? 0
                            : leftPlateNums[rightCandleIndices[query[0]] - 1]))
                    : 0)
        .toArray();
  }
}
