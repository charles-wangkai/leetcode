import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public long findScore(int[] nums) {
    long result = 0;
    boolean[] marked = new boolean[nums.length];
    int[] sortedIndices =
        IntStream.range(0, nums.length)
            .boxed()
            .sorted(Comparator.<Integer, Integer>comparing(i -> nums[i]).thenComparing(i -> i))
            .mapToInt(Integer::intValue)
            .toArray();
    for (int index : sortedIndices) {
      if (!marked[index]) {
        result += nums[index];

        for (int i = -1; i <= 1; ++i) {
          int next = index + i;
          if (next >= 0 && next < marked.length) {
            marked[next] = true;
          }
        }
      }
    }

    return result;
  }
}
