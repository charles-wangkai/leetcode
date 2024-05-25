import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
    int[] indices = IntStream.range(0, nums.length).filter(i -> nums[i] == x).toArray();

    return Arrays.stream(queries)
        .map(query -> (query <= indices.length) ? indices[query - 1] : -1)
        .toArray();
  }
}