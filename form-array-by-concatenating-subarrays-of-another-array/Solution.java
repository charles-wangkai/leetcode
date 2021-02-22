import java.util.stream.IntStream;

class Solution {
  public boolean canChoose(int[][] groups, int[] nums) {
    int groupIndex = 0;
    int numIndex = 0;
    while (groupIndex != groups.length) {
      if (numIndex == nums.length) {
        return false;
      }

      int groupIndex_ = groupIndex;
      int numIndex_ = numIndex;
      if (numIndex + groups[groupIndex].length <= nums.length
          && IntStream.range(0, groups[groupIndex].length)
              .allMatch(i -> groups[groupIndex_][i] == nums[numIndex_ + i])) {
        numIndex += groups[groupIndex].length;
        ++groupIndex;
      } else {
        ++numIndex;
      }
    }

    return true;
  }
}
