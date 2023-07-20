import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumSubarrayMins(int[] arr) {
    int[] leftIndices = new int[arr.length];
    Deque<Integer> leftAscendingIndices = new ArrayDeque<>();
    for (int i = 0; i < leftIndices.length; ++i) {
      while (!leftAscendingIndices.isEmpty() && arr[i] <= arr[leftAscendingIndices.peek()]) {
        leftAscendingIndices.pop();
      }

      leftIndices[i] = (leftAscendingIndices.isEmpty() ? -1 : leftAscendingIndices.peek()) + 1;
      leftAscendingIndices.push(i);
    }

    int[] rightIndices = new int[arr.length];
    Deque<Integer> rightAscendingIndices = new ArrayDeque<>();
    for (int i = rightIndices.length - 1; i >= 0; --i) {
      while (!rightAscendingIndices.isEmpty() && arr[i] < arr[rightAscendingIndices.peek()]) {
        rightAscendingIndices.pop();
      }

      rightIndices[i] =
          (rightAscendingIndices.isEmpty() ? rightIndices.length : rightAscendingIndices.peek())
              - 1;
      rightAscendingIndices.push(i);
    }

    return IntStream.range(0, arr.length)
        .map(i -> multiplyMod(arr[i], multiplyMod(i - leftIndices[i] + 1, rightIndices[i] - i + 1)))
        .reduce(this::addMod)
        .getAsInt();
  }

  int addMod(int x, int y) {
    return Math.floorMod(x + y, MODULUS);
  }

  int multiplyMod(int x, int y) {
    return Math.floorMod((long) x * y, MODULUS);
  }
}
