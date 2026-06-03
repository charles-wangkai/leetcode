import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
  public int[] maximumMEX(int[] nums) {
    Map<Integer, Queue<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayDeque<>());
      valueToIndices.get(nums[i]).offer(i);
    }

    List<Integer> result = new ArrayList<>();
    int beginIndex = 0;
    while (beginIndex != nums.length) {
      int endIndex = beginIndex;
      int mex = 0;
      while (true) {
        while (!valueToIndices.getOrDefault(mex, new ArrayDeque<>()).isEmpty()
            && valueToIndices.get(mex).peek() < beginIndex) {
          valueToIndices.get(mex).poll();
        }
        if (valueToIndices.getOrDefault(mex, new ArrayDeque<>()).isEmpty()) {
          break;
        }

        endIndex = Math.max(endIndex, valueToIndices.get(mex).poll());
        ++mex;
      }

      result.add(mex);
      beginIndex = endIndex + 1;
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }
}