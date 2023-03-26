import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Long> minOperations(int[] nums, int[] queries) {
    Arrays.sort(nums);

    long[] result = new long[queries.length];
    long total = Arrays.stream(nums).asLongStream().sum();
    int[] sortedQueryIndices =
        IntStream.range(0, queries.length)
            .boxed()
            .sorted(Comparator.comparing(i -> queries[i]))
            .mapToInt(Integer::intValue)
            .toArray();
    int index = 0;
    long leftSum = 0;
    for (int queryIndex : sortedQueryIndices) {
      while (index != nums.length && nums[index] < queries[queryIndex]) {
        leftSum += nums[index];
        ++index;
      }

      result[queryIndex] =
          ((long) index * queries[queryIndex] - leftSum)
              + (total - leftSum - (long) (nums.length - index) * queries[queryIndex]);
    }

    return Arrays.stream(result).boxed().toList();
  }
}
