import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int maxSubarray(int[] nums) {
    int[] maxRights = new int[nums.length];
    Arrays.fill(maxRights, nums.length - 1);

    for (int middleIndex = 0; middleIndex < nums.length; ++middleIndex) {
      Map<Integer, Integer> leftValueToMaxIndex = new HashMap<>();
      for (int i = 0; i < middleIndex; ++i) {
        leftValueToMaxIndex.put(nums[i], i);
      }

      Map<Integer, Integer> rightValueToMinIndex = new HashMap<>();
      for (int i = nums.length - 1; i > middleIndex; --i) {
        rightValueToMinIndex.put(nums[i], i);
      }

      for (int leftValue : leftValueToMaxIndex.keySet()) {
        int leftIndex = leftValueToMaxIndex.get(leftValue);
        for (int rightValue :
            new int[] {
              leftValue + nums[middleIndex],
              leftValue - nums[middleIndex],
              nums[middleIndex] - leftValue
            }) {
          if (rightValueToMinIndex.containsKey(rightValue)) {
            maxRights[leftIndex] =
                Math.min(maxRights[leftIndex], rightValueToMinIndex.get(rightValue) - 1);
          }
        }
      }
    }

    return IntStream.range(0, nums.length)
        .map(
            beginIndex -> {
              int endIndex = beginIndex;
              int maxRight = maxRights[beginIndex];
              while (endIndex != maxRights.length - 1 && endIndex + 1 <= maxRight) {
                ++endIndex;
                maxRight = Math.min(maxRight, maxRights[endIndex]);
              }

              return endIndex - beginIndex + 1;
            })
        .max()
        .getAsInt();
  }
}