import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long maximumSumOfHeights(List<Integer> maxHeights) {
    int n = maxHeights.size();

    long[] leftSums = new long[n];
    long leftSum = 0;
    Deque<Integer> leftIndices = new ArrayDeque<>();
    leftIndices.push(-1);
    for (int i = 0; i < leftSums.length; ++i) {
      while (leftIndices.peek() != -1 && maxHeights.get(i) < maxHeights.get(leftIndices.peek())) {
        int top = leftIndices.pop();
        leftSum -= (long) maxHeights.get(top) * (top - leftIndices.peek());
      }

      leftSum += (long) maxHeights.get(i) * (i - leftIndices.peek());
      leftIndices.push(i);

      leftSums[i] = leftSum;
    }

    long[] rightSums = new long[n];
    long rightSum = 0;
    Deque<Integer> rightIndices = new ArrayDeque<>();
    rightIndices.push(n);
    for (int i = rightSums.length - 1; i >= 0; --i) {
      while (rightIndices.peek() != n && maxHeights.get(i) < maxHeights.get(rightIndices.peek())) {
        int top = rightIndices.pop();
        rightSum -= (long) maxHeights.get(top) * (rightIndices.peek() - top);
      }

      rightSum += (long) maxHeights.get(i) * (rightIndices.peek() - i);
      rightIndices.push(i);

      rightSums[i] = rightSum;
    }

    return IntStream.range(0, n)
        .mapToLong(i -> leftSums[i] + rightSums[i] - maxHeights.get(i))
        .max()
        .getAsLong();
  }
}
