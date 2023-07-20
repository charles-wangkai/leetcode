import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    Map<Integer, Integer> numToGreater = new HashMap<>();
    Deque<Integer> stack = new ArrayDeque<>();
    for (int num : nums2) {
      while (!stack.isEmpty() && num > stack.peek()) {
        numToGreater.put(stack.pop(), num);
      }

      stack.push(num);
    }

    return IntStream.range(0, nums1.length)
        .map(i -> numToGreater.getOrDefault(nums1[i], -1))
        .toArray();
  }
}
