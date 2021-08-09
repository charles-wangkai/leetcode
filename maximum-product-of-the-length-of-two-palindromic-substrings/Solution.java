// https://leetcode.com/problems/maximum-product-of-the-length-of-two-palindromic-substrings/discuss/1389958/Manacher-and-Queue

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.stream.IntStream;

class Solution {
  public long maxProduct(String s) {
    int[] m = new int[s.length()];
    int l = -1;
    int r = -1;
    for (int i = 0; i < m.length; ++i) {
      m[i] = (i > r) ? 1 : Math.min(m[l + r - i], r - i + 1);
      while (i - m[i] >= 0 && i + m[i] < s.length() && s.charAt(i - m[i]) == s.charAt(i + m[i])) {
        ++m[i];
      }

      if (i + m[i] - 1 > r) {
        l = i - m[i] + 1;
        r = i + m[i] - 1;
      }
    }

    int[] rightMaxLengths = new int[s.length()];
    int rightMaxLength = -1;
    Queue<Integer> rightCenters = new ArrayDeque<>();
    for (int i = rightMaxLengths.length - 1; i >= 0; --i) {
      while (!rightCenters.isEmpty() && rightCenters.peek() - m[rightCenters.peek()] + 1 > i) {
        rightCenters.poll();
      }

      rightMaxLength =
          Math.max(
              rightMaxLength, 1 + (rightCenters.isEmpty() ? 0 : ((rightCenters.peek() - i) * 2)));
      rightMaxLengths[i] = rightMaxLength;

      rightCenters.offer(i);
    }

    int[] leftMaxLengths = new int[s.length()];
    int leftMaxLength = -1;
    Queue<Integer> leftCenters = new ArrayDeque<>();
    for (int i = 0; i < leftMaxLengths.length; ++i) {
      while (!leftCenters.isEmpty() && leftCenters.peek() + m[leftCenters.peek()] - 1 < i) {
        leftCenters.poll();
      }

      leftMaxLength =
          Math.max(leftMaxLength, 1 + (leftCenters.isEmpty() ? 0 : ((i - leftCenters.peek()) * 2)));
      leftMaxLengths[i] = leftMaxLength;

      leftCenters.offer(i);
    }

    return IntStream.range(0, s.length() - 1)
        .mapToLong(i -> (long) leftMaxLengths[i] * rightMaxLengths[i + 1])
        .max()
        .getAsLong();
  }
}
