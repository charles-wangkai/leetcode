import java.util.stream.IntStream;

class Solution {
  public int minFlipsMonoIncr(String s) {
    int[] leftOneNums = new int[s.length()];
    for (int i = 0; i < leftOneNums.length; ++i) {
      leftOneNums[i] = ((i == 0) ? 0 : leftOneNums[i - 1]) + ((s.charAt(i) == '1') ? 1 : 0);
    }

    int[] rightZeroNums = new int[s.length()];
    for (int i = rightZeroNums.length - 1; i >= 0; --i) {
      rightZeroNums[i] =
          ((i == rightZeroNums.length - 1) ? 0 : rightZeroNums[i + 1])
              + ((s.charAt(i) == '0') ? 1 : 0);
    }

    return IntStream.concat(
            IntStream.of(rightZeroNums[0], leftOneNums[leftOneNums.length - 1]),
            IntStream.range(0, s.length() - 1).map(i -> leftOneNums[i] + rightZeroNums[i + 1]))
        .min()
        .getAsInt();
  }
}
