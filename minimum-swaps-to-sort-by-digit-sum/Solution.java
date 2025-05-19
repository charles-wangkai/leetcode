import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minSwaps(int[] nums) {
    int[] digitSums =
        Arrays.stream(nums).map(x -> String.valueOf(x).chars().map(c -> c - '0').sum()).toArray();

    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(
                Comparator.<Integer, Integer>comparing(i -> digitSums[i])
                    .thenComparing(i -> nums[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    Map<Integer, Integer> indexToTarget =
        IntStream.range(0, sortedIndices.length)
            .boxed()
            .collect(Collectors.toMap(i -> sortedIndices[i], i -> i));

    int result = 0;
    boolean[] visited = new boolean[nums.length];
    for (int i = 0; i < nums.length; ++i) {
      if (!visited[i]) {
        result += findLoop(nums, indexToTarget, visited, i) - 1;
      }
    }

    return result;
  }

  int findLoop(int[] nums, Map<Integer, Integer> indexToTarget, boolean[] visited, int index) {
    int result = 0;
    while (!visited[index]) {
      visited[index] = true;
      ++result;

      index = indexToTarget.get(index);
    }

    return result;
  }
}