import java.util.stream.IntStream;

class Solution {
  public int[] rotateElements(int[] nums, int k) {
    int[] result = nums.clone();
    int[] indices = IntStream.range(0, nums.length).filter(i -> nums[i] >= 0).toArray();
    for (int i = 0; i < indices.length; ++i) {
      result[indices[Math.floorMod(i - k, indices.length)]] = nums[indices[i]];
    }

    return result;
  }
}