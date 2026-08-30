import java.util.stream.IntStream;

class Solution {
  public int maxValidSplits(int[] nums) {
    return Math.max(
        computeScore(nums),
        IntStream.range(0, nums.length)
            .map(
                i ->
                    computeScore(
                        IntStream.range(0, nums.length)
                            .filter(j -> j != i)
                            .map(j -> nums[j])
                            .toArray()))
            .max()
            .getAsInt());
  }

  int computeScore(int[] values) {
    if (values.length == 0) {
      return 0;
    }

    int[] leftGcds = new int[values.length];
    leftGcds[0] = values[0];
    for (int i = 1; i < leftGcds.length; ++i) {
      leftGcds[i] = gcd(leftGcds[i - 1], values[i]);
    }

    int[] rightGcds = new int[values.length];
    rightGcds[rightGcds.length - 1] = values[values.length - 1];
    for (int i = rightGcds.length - 2; i >= 0; --i) {
      rightGcds[i] = gcd(rightGcds[i + 1], values[i]);
    }

    return (int)
        IntStream.range(0, values.length - 1).filter(i -> leftGcds[i] == rightGcds[i + 1]).count();
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }
}