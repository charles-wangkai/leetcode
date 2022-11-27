import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int countSubarrays(int[] nums, int k) {
    int index = IntStream.range(0, nums.length).filter(i -> nums[i] == k).findAny().getAsInt();

    Map<Integer, Integer> rightDeltaToCount = new HashMap<>();
    int rightDelta = 0;
    for (int i = index; i < nums.length; ++i) {
      if (i != index) {
        rightDelta += (nums[i] > k) ? 1 : -1;
      }

      rightDeltaToCount.put(rightDelta, rightDeltaToCount.getOrDefault(rightDelta, 0) + 1);
    }

    int result = 0;
    int leftDelta = 0;
    for (int i = index; i >= 0; --i) {
      if (i != index) {
        leftDelta += (nums[i] > k) ? 1 : -1;
      }

      result +=
          rightDeltaToCount.getOrDefault(-leftDelta, 0)
              + rightDeltaToCount.getOrDefault(1 - leftDelta, 0);
    }

    return result;
  }
}
