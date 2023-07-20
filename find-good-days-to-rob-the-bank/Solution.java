import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> goodDaysToRobBank(int[] security, int time) {
    int[] leftNums = new int[security.length];
    for (int i = 0; i < leftNums.length; ++i) {
      leftNums[i] = (i == 0 || security[i - 1] < security[i]) ? 1 : (1 + leftNums[i - 1]);
    }

    int[] rightNums = new int[security.length];
    for (int i = rightNums.length - 1; i >= 0; --i) {
      rightNums[i] =
          (i == rightNums.length - 1 || security[i + 1] < security[i]) ? 1 : (1 + rightNums[i + 1]);
    }

    return IntStream.range(0, security.length)
        .filter(i -> leftNums[i] >= time + 1 && rightNums[i] >= time + 1)
        .boxed()
        .toList();
  }
}
