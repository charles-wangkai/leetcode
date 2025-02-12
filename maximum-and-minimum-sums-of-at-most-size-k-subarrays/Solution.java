// https://chatgpt.com/share/67ab65fc-4eec-8002-9fc8-ab06a08f4205

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

class Solution {
  public long minMaxSubarraySum(int[] nums, int k) {
    int[] leftMinIndices = buildLeftMinIndices(nums);
    int[] rightMinIndices = buildRightMinIndices(nums);
    int[] leftMaxIndices = buildLeftMaxIndices(nums);
    int[] rightMaxIndices = buildRightMaxIndices(nums);

    return IntStream.range(0, nums.length)
        .mapToLong(
            i ->
                nums[i]
                    * (computeNum(k, i - leftMinIndices[i], rightMinIndices[i] - i)
                        + computeNum(k, i - leftMaxIndices[i], rightMaxIndices[i] - i)))
        .sum();
  }

  int[] buildLeftMinIndices(int[] nums) {
    int[] result = new int[nums.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < result.length; ++i) {
      while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
        stack.pop();
      }

      result[i] = stack.isEmpty() ? 0 : (stack.peek() + 1);
      stack.push(i);
    }

    return result;
  }

  int[] buildRightMinIndices(int[] nums) {
    int[] result = new int[nums.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = result.length - 1; i >= 0; --i) {
      while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
        stack.pop();
      }

      result[i] = stack.isEmpty() ? (nums.length - 1) : (stack.peek() - 1);
      stack.push(i);
    }

    return result;
  }

  int[] buildLeftMaxIndices(int[] nums) {
    int[] result = new int[nums.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < result.length; ++i) {
      while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
        stack.pop();
      }

      result[i] = stack.isEmpty() ? 0 : (stack.peek() + 1);
      stack.push(i);
    }

    return result;
  }

  int[] buildRightMaxIndices(int[] nums) {
    int[] result = new int[nums.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = result.length - 1; i >= 0; --i) {
      while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
        stack.pop();
      }

      result[i] = stack.isEmpty() ? (nums.length - 1) : (stack.peek() - 1);
      stack.push(i);
    }

    return result;
  }

  long computeNum(int k, int leftLength, int rightLength) {
    int Lmax = Math.min(leftLength, k - 1);

    int T = Math.max(0, k - 1 - rightLength);
    if (T > Lmax) {
      return (Lmax + 1L) * (rightLength + 1);
    }

    long sum1 = T * (rightLength + 1L);

    int N = Lmax - T + 1;
    long sumL = (Lmax * (Lmax + 1L) - (T - 1L) * T) / 2;
    long sum2 = (long) N * k - sumL;

    return sum1 + sum2;
  }
}