import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long[] unmarkedSumArray(int[] nums, int[][] queries) {
    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.<Integer, Integer>comparing(i -> nums[i]).thenComparing(i -> i))
            .mapToInt(Integer::intValue)
            .toArray();

    long[] result = new long[queries.length];
    long unmarkedSum = Arrays.stream(nums).asLongStream().sum();
    boolean[] marked = new boolean[nums.length];
    int index = 0;
    for (int i = 0; i < queries.length; ++i) {
      if (!marked[queries[i][0]]) {
        marked[queries[i][0]] = true;
        unmarkedSum -= nums[queries[i][0]];
      }

      while (queries[i][1] != 0 && index != sortedIndices.length) {
        if (!marked[sortedIndices[index]]) {
          marked[sortedIndices[index]] = true;
          unmarkedSum -= nums[sortedIndices[index]];
          --queries[i][1];
        }

        ++index;
      }

      result[i] = unmarkedSum;
    }

    return result;
  }
}