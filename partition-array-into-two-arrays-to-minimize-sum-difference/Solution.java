import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
  public int minimumDifference(int[] nums) {
    int n = nums.length / 2;

    int total = Arrays.stream(nums).sum();

    @SuppressWarnings("unchecked")
    List<Integer>[] leftSumLists = new List[n + 1];
    for (int i = 0; i < leftSumLists.length; ++i) {
      leftSumLists[i] = new ArrayList<>();
    }
    for (int code = 0; code < 1 << n; ++code) {
      int leftSum = 0;
      for (int i = 0; i < n; ++i) {
        if ((code & (1 << i)) != 0) {
          leftSum += nums[i];
        }
      }

      leftSumLists[Integer.bitCount(code)].add(leftSum);
    }
    for (int i = 0; i < leftSumLists.length; ++i) {
      Collections.sort(leftSumLists[i]);
    }

    int result = Integer.MAX_VALUE;
    for (int code = 0; code < 1 << n; ++code) {
      int rightSum = 0;
      for (int i = 0; i < n; ++i) {
        if ((code & (1 << i)) != 0) {
          rightSum += nums[n + i];
        }
      }

      List<Integer> leftSumList = leftSumLists[n - Integer.bitCount(code)];
      for (int i = -1; i <= 1; ++i) {
        int sumTarget = (total + i) / 2;
        int index = Collections.binarySearch(leftSumList, sumTarget - rightSum);
        if (index < 0) {
          index = -1 - index;
        }

        if (index != leftSumList.size()) {
          result = Math.min(result, Math.abs(total - (leftSumList.get(index) + rightSum) * 2));
        }
        if (index != 0) {
          result = Math.min(result, Math.abs(total - (leftSumList.get(index - 1) + rightSum) * 2));
        }
      }
    }

    return result;
  }
}
