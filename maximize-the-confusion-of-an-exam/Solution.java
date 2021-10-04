import java.util.stream.IntStream;

class Solution {
  public int maxConsecutiveAnswers(String answerKey, int k) {
    return Math.max(computeMaxLength(answerKey, k, 'T'), computeMaxLength(answerKey, k, 'F'));
  }

  int computeMaxLength(String answerKey, int k, char target) {
    int n = answerKey.length();

    int[] leftIndices = IntStream.range(0, n).toArray();
    for (int i = 1; i < leftIndices.length; ++i) {
      if (answerKey.charAt(i - 1) == target) {
        leftIndices[i] = leftIndices[i - 1];
      }
    }

    int[] rightIndices = IntStream.range(0, n).toArray();
    for (int i = rightIndices.length - 2; i >= 0; --i) {
      if (answerKey.charAt(i + 1) == target) {
        rightIndices[i] = rightIndices[i + 1];
      }
    }

    int[] indices = IntStream.range(0, n).filter(i -> answerKey.charAt(i) != target).toArray();

    return IntStream.rangeClosed(0, indices.length - k)
        .map(i -> rightIndices[indices[i + k - 1]] - leftIndices[indices[i]] + 1)
        .max()
        .orElse(answerKey.length());
  }
}
