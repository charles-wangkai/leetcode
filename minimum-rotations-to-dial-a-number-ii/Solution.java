import java.util.stream.IntStream;

class Solution {
  public int minRotations(int n, String s) {
    int[] leftCosts = new int[n];
    for (int i = 0; i < leftCosts.length; ++i) {
      leftCosts[i] =
          ((i == 0) ? 0 : leftCosts[i - 1])
              + computeCost(s.charAt(i), (i == 0) ? '0' : s.charAt(i - 1));
    }

    int[] rightCosts = new int[n];
    for (int i = rightCosts.length - 2; i >= 0; --i) {
      rightCosts[i] = rightCosts[i + 1] + computeCost(s.charAt(i), s.charAt(i + 1));
    }

    return Math.min(
        Math.min(
            leftCosts[leftCosts.length - 1],
            computeCost('0', s.charAt(s.length() - 1)) + rightCosts[0]),
        IntStream.range(0, n - 1)
            .map(
                i ->
                    leftCosts[i]
                        + computeCost(s.charAt(i), s.charAt(s.length() - 1))
                        + rightCosts[i + 1])
            .min()
            .orElse(Integer.MAX_VALUE));
  }

  int computeCost(char c1, char c2) {
    int distance = Math.abs(c1 - c2);

    return Math.min(distance, 10 - distance);
  }
}