import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public long maxScore(int[] nums) {
    List<Integer> indices = new ArrayList<>();
    for (int i = 0; i < nums.length; ++i) {
      while (!indices.isEmpty() && nums[indices.getLast()] <= nums[i]) {
        indices.removeLast();
      }

      indices.add(i);
    }

    return IntStream.range(0, indices.size())
        .mapToLong(
            i ->
                (long) (indices.get(i) - ((i == 0) ? 0 : indices.get(i - 1)))
                    * nums[indices.get(i)])
        .sum();
  }
}