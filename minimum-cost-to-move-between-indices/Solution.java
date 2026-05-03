import java.util.Arrays;

class Solution {
  public int[] minCost(int[] nums, int[][] queries) {
    boolean[] leftOrRights = new boolean[nums.length];
    for (int i = 0; i < leftOrRights.length; ++i) {
      leftOrRights[i] =
          i == leftOrRights.length - 1
              || (i != 0 && nums[i] - nums[i - 1] <= nums[i + 1] - nums[i]);
    }

    int[] leftPrefixSaves = new int[nums.length + 1];
    int[] rightPrefixSaves = new int[nums.length + 1];
    for (int i = 1; i <= nums.length; ++i) {
      leftPrefixSaves[i] =
          leftPrefixSaves[i - 1] + (leftOrRights[i - 1] ? (nums[i - 1] - nums[i - 2] - 1) : 0);
      rightPrefixSaves[i] =
          rightPrefixSaves[i - 1]
              + ((i == 1 || leftOrRights[i - 2]) ? 0 : (nums[i - 1] - nums[i - 2] - 1));
    }

    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              if (query[0] < query[1]) {
                return nums[query[1]]
                    - nums[query[0]]
                    - (rightPrefixSaves[query[1] + 1] - rightPrefixSaves[query[0] + 1]);
              }

              return nums[query[0]]
                  - nums[query[1]]
                  - (leftPrefixSaves[query[0] + 1] - leftPrefixSaves[query[1] + 1]);
            })
        .toArray();
  }
}