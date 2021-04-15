import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int minSwaps(int[] data) {
    int oneCount = (int) Arrays.stream(data).filter(x -> x == 1).count();
    if (oneCount == 0) {
      return 0;
    }

    int sum = IntStream.range(0, oneCount).map(i -> data[i]).sum();
    int maxSum = sum;
    for (int i = oneCount; i < data.length; ++i) {
      sum += data[i] - data[i - oneCount];
      maxSum = Math.max(maxSum, sum);
    }

    return oneCount - maxSum;
  }
}
