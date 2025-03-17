import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public List<Integer> solveQueries(int[] nums, int[] queries) {
    Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
    for (int i = 0; i < nums.length; ++i) {
      valueToIndices.putIfAbsent(nums[i], new ArrayList<>());
      valueToIndices.get(nums[i]).add(i);
    }

    return Arrays.stream(queries)
        .map(
            query -> {
              List<Integer> indices = valueToIndices.get(nums[query]);
              if (indices.size() == 1) {
                return -1;
              }

              int pos = Collections.binarySearch(indices, query);

              return Math.min(
                  (pos == 0)
                      ? (query + nums.length - indices.getLast())
                      : (query - indices.get(pos - 1)),
                  (pos == indices.size() - 1)
                      ? (indices.get(0) + nums.length - query)
                      : (indices.get(pos + 1) - query));
            })
        .boxed()
        .toList();
  }
}