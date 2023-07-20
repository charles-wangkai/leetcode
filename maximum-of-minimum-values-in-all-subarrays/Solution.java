import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int[] findMaximums(int[] nums) {
    int[] leftLengths = buildLeftLengths(nums);
    int[] rightLengths = reverse(buildLeftLengths(reverse(nums)));

    Map<Integer, Integer> valueToMaxLength = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToMaxLength.put(
          nums[i],
          Math.max(
              valueToMaxLength.getOrDefault(nums[i], 0), 1 + leftLengths[i] + rightLengths[i]));
    }

    int[] result = new int[nums.length];
    int[] sorted =
        Arrays.stream(nums)
            .boxed()
            .distinct()
            .sorted(Comparator.reverseOrder())
            .mapToInt(x -> x)
            .toArray();
    int sortedIndex = 0;
    for (int i = 0; i < result.length; ++i) {
      while (valueToMaxLength.get(sorted[sortedIndex]) < i + 1) {
        ++sortedIndex;
      }
      result[i] = sorted[sortedIndex];
    }

    return result;
  }

  int[] buildLeftLengths(int[] a) {
    int[] result = new int[a.length];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i < a.length; ++i) {
      while (!stack.isEmpty() && a[stack.peek()] >= a[i]) {
        stack.pop();
      }

      result[i] = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;

      stack.push(i);
    }

    return result;
  }

  int[] reverse(int[] a) {
    return IntStream.range(0, a.length).map(i -> a[a.length - 1 - i]).toArray();
  }
}
