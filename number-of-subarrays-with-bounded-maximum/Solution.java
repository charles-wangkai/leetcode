import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

class Solution {
  public int numSubarrayBoundedMax(int[] nums, int left, int right) {
    int[] leftIndices = new int[nums.length];
    Deque<Integer> leftMaxs = new ArrayDeque<>();
    for (int i = 0; i < nums.length; ++i) {
      while (!leftMaxs.isEmpty() && nums[leftMaxs.peek()] < nums[i]) {
        leftMaxs.pop();
      }

      leftIndices[i] = leftMaxs.isEmpty() ? 0 : (leftMaxs.peek() + 1);

      leftMaxs.push(i);
    }

    int[] rightIndices = new int[nums.length];
    Deque<Integer> rightMaxs = new ArrayDeque<>();
    for (int i = nums.length - 1; i >= 0; --i) {
      while (!rightMaxs.isEmpty() && nums[rightMaxs.peek()] <= nums[i]) {
        rightMaxs.pop();
      }

      rightIndices[i] = rightMaxs.isEmpty() ? (nums.length - 1) : (rightMaxs.peek() - 1);

      rightMaxs.push(i);
    }

    return IntStream.range(0, nums.length)
        .filter(i -> nums[i] >= left && nums[i] <= right)
        .map(i -> (i - leftIndices[i] + 1) * (rightIndices[i] - i + 1))
        .sum();
  }
}
