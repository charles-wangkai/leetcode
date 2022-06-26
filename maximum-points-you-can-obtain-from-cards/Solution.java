import java.util.stream.IntStream;

class Solution {
  public int maxScore(int[] cardPoints, int k) {
    int leftIndex = k - 1;
    int rightIndex = cardPoints.length;
    int score = IntStream.range(0, k).map(i -> cardPoints[i]).sum();
    int result = score;
    while (leftIndex >= 0) {
      score += cardPoints[rightIndex - 1] - cardPoints[leftIndex];
      result = Math.max(result, score);

      --leftIndex;
      --rightIndex;
    }

    return result;
  }
}