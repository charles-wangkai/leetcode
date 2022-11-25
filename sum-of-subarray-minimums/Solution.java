import java.util.Stack;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int sumSubarrayMins(int[] arr) {
    int[] leftIndices = new int[arr.length];
    Stack<Integer> leftAscendingIndices = new Stack<>();
    for (int i = 0; i < leftIndices.length; ++i) {
      while (!leftAscendingIndices.empty() && arr[i] <= arr[leftAscendingIndices.peek()]) {
        leftAscendingIndices.pop();
      }

      leftIndices[i] = (leftAscendingIndices.empty() ? -1 : leftAscendingIndices.peek()) + 1;
      leftAscendingIndices.push(i);
    }

    int[] rightIndices = new int[arr.length];
    Stack<Integer> rightAscendingIndices = new Stack<>();
    for (int i = rightIndices.length - 1; i >= 0; --i) {
      while (!rightAscendingIndices.empty() && arr[i] < arr[rightAscendingIndices.peek()]) {
        rightAscendingIndices.pop();
      }

      rightIndices[i] =
          (rightAscendingIndices.empty() ? rightIndices.length : rightAscendingIndices.peek()) - 1;
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
