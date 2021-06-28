import java.util.stream.IntStream;

class Solution {
  public int candy(int[] ratings) {
    int n = ratings.length;

    int[] leftMins = new int[n];
    for (int i = 0; i < leftMins.length; ++i) {
      if (i != 0 && ratings[i] > ratings[i - 1]) {
        leftMins[i] = leftMins[i - 1] + 1;
      } else {
        leftMins[i] = 1;
      }
    }

    int[] rightMins = new int[n];
    for (int i = rightMins.length - 1; i >= 0; --i) {
      if (i != rightMins.length - 1 && ratings[i] > ratings[i + 1]) {
        rightMins[i] = rightMins[i + 1] + 1;
      } else {
        rightMins[i] = 1;
      }
    }

    return IntStream.range(0, n).map(i -> Math.max(leftMins[i], rightMins[i])).sum();
  }
}
