import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int subarraysWithKDistinct(int[] nums, int k) {
    int result = 0;
    Map<Integer, Queue<Integer>> valueToIndices = new HashMap<>();
    int minIndex = -1;
    int endIndex = -1;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      while (endIndex != nums.length - 1
          && (valueToIndices.size() != k || valueToIndices.containsKey(nums[endIndex + 1]))) {
        ++endIndex;
        add(valueToIndices, nums[endIndex], endIndex);

        if (valueToIndices.size() == k && minIndex == -1) {
          minIndex = endIndex;
        }
      }

      if (minIndex == -1) {
        break;
      }

      result += endIndex - minIndex + 1;

      remove(valueToIndices, nums[beginIndex]);

      if (valueToIndices.containsKey(nums[beginIndex])) {
        minIndex = Math.max(minIndex, valueToIndices.get(nums[beginIndex]).peek());
      } else {
        minIndex = -1;
      }
    }

    return result;
  }

  void add(Map<Integer, Queue<Integer>> valueToIndices, int value, int index) {
    valueToIndices.putIfAbsent(value, new ArrayDeque<>());
    valueToIndices.get(value).offer(index);
  }

  void remove(Map<Integer, Queue<Integer>> valueToIndices, int value) {
    valueToIndices.get(value).poll();

    if (valueToIndices.get(value).isEmpty()) {
      valueToIndices.remove(value);
    }
  }
}
