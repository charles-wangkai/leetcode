import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int longestEqualSubarray(List<Integer> nums, int k) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.size(); ++i) {
      valueToIndices.putIfAbsent(nums.get(i), new ArrayList<>());
      valueToIndices.get(nums.get(i)).add(i);
    }

    int result = 0;
    for (List<Integer> indices : valueToIndices.values()) {
      int endIndex = -1;
      for (int beginIndex = 0; beginIndex < indices.size(); ++beginIndex) {
        while (endIndex + 1 != indices.size()
            && (indices.get(endIndex + 1) - indices.get(beginIndex)) - (endIndex + 1 - beginIndex)
                <= k) {
          ++endIndex;
        }

        result = Math.max(result, endIndex - beginIndex + 1);
      }
    }

    return result;
  }
}
