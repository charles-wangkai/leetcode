import java.util.stream.IntStream;

class Solution {
  public int candy(int[] ratings) {
    int n = ratings.length;

    int[] leftMins = new int[n];
    for (int i = 0; i < leftMins.length; ++i) {
      leftMins[i] = (i == 0 || ratings[i] <= ratings[i - 1]) ? 1 : (leftMins[i - 1] + 1);
    }

    int[] rightMins = new int[n];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      rightMins[i] =
          (i == rightMins.length - 1 || ratings[i] <= ratings[i + 1]) ? 1 : (rightMins[i + 1] + 1);
    }

    return IntStream.range(0, n).map(i -> Math.max(leftMins[i], rightMins[i])).sum();
  }
}
