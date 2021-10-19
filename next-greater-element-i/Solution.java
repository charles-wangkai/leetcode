import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.IntStream;

class Solution {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> numToGreater = new HashMap<>();
    Stack<Integer> stack = new Stack<>();
    for (int num : nums2) {
      while (!stack.empty() && num > stack.peek()) {
        numToGreater.put(stack.pop(), num);
      }

      stack.push(num);
    }

    return IntStream.range(0, nums1.length)
        .map(i -> numToGreater.getOrDefault(nums1[i], -1))
        .toArray();
  }
}
