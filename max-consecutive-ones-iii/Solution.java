import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int longestOnes(int[] nums, int k) {
    List<Integer> zeroIndices = new ArrayList<>();
    zeroIndices.add(-1);
    for (int i = 0; i < nums.length; ++i) {
      if (nums[i] == 0) {
        zeroIndices.add(i);
      }
    }
    zeroIndices.add(nums.length);

    if (zeroIndices.size() - 2 <= k) {
      return nums.length;
    }

    return IntStream.range(0, zeroIndices.size() - k - 1)
        .map(i -> zeroIndices.get(i + k + 1) - zeroIndices.get(i) - 1)
        .max()
        .getAsInt();
  }
}
