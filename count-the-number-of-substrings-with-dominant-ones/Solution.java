import java.util.stream.IntStream;

class Solution {
  public int numberOfSubstrings(String s) {
    int[] zeroIndices = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '0').toArray();

    int result =
        IntStream.rangeClosed(0, zeroIndices.length)
            .map(
                i ->
                    ((i == zeroIndices.length) ? s.length() : zeroIndices[i])
                        - ((i == 0) ? -1 : zeroIndices[i - 1])
                        - 1)
            .map(oneNum -> oneNum * (oneNum + 1) / 2)
            .sum();
    for (int beginIndex = 0; beginIndex < zeroIndices.length; ++beginIndex) {
      int oneNum = 0;
      for (int endIndex = beginIndex; endIndex < zeroIndices.length; ++endIndex) {
        if (endIndex != beginIndex) {
          oneNum += zeroIndices[endIndex] - zeroIndices[endIndex - 1] - 1;
        }

        int oneTarget = (endIndex - beginIndex + 1) * (endIndex - beginIndex + 1);
        if (oneTarget > s.length()) {
          break;
        }
        int oneExtra = Math.max(0, oneTarget - oneNum);

        int leftLength =
            zeroIndices[beginIndex] - ((beginIndex == 0) ? -1 : zeroIndices[beginIndex - 1]) - 1;
        int rightLength =
            ((endIndex == zeroIndices.length - 1) ? s.length() : zeroIndices[endIndex + 1])
                - zeroIndices[endIndex]
                - 1;
        for (int left = 0; left <= leftLength; ++left) {
          for (int right = 0; right <= rightLength; ++right) {
            if (left + right >= oneExtra) {
              ++result;
            }
          }
        }
      }
    }

    return result;
  }
}