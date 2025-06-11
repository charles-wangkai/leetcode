import java.util.stream.IntStream;

class Solution {
  public boolean canMakeEqual(int[] nums, int k) {
    return check(k, IntStream.range(0, nums.length).filter(i -> nums[i] == 1).toArray())
        || check(k, IntStream.range(0, nums.length).filter(i -> nums[i] == -1).toArray());
  }

  boolean check(int k, int[] indices) {
    return indices.length % 2 == 0
        && (indices.length / 2
                + IntStream.range(0, indices.length / 2)
                    .map(i -> indices[i * 2 + 1] - indices[i * 2] - 1)
                    .sum())
            <= k;
  }
}