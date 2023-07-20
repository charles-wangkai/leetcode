import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution {
  public int[] nextGreaterElements(int[] nums) {
    int[] result = new int[nums.length];
    Arrays.fill(result, -1);

    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < nums.length * 2; i++) {
      int index = i % nums.length;

      while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
        result[stack.pop()] = nums[index];
      }

      stack.push(index);
    }

    return result;
  }
}
