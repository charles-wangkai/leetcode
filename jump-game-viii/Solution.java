import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
  public long minCost(int[] nums, int[] costs) {
    int n = nums.length;

    int[] nextNonDecreasingIndices = new int[n];
    Deque<Integer> nonDecreasingIndices = new ArrayDeque<>();
    for (int i = nextNonDecreasingIndices.length - 1; i >= 0; --i) {
      while (!nonDecreasingIndices.isEmpty() && nums[nonDecreasingIndices.peek()] < nums[i]) {
        nonDecreasingIndices.pop();
      }
      nextNonDecreasingIndices[i] =
          nonDecreasingIndices.isEmpty() ? -1 : nonDecreasingIndices.peek();
      nonDecreasingIndices.push(i);
    }

    int[] nextDecreasingIndices = new int[n];
    Deque<Integer> decreasingIndices = new ArrayDeque<>();
    for (int i = nextDecreasingIndices.length - 1; i >= 0; --i) {
      while (!decreasingIndices.isEmpty() && nums[decreasingIndices.peek()] >= nums[i]) {
        decreasingIndices.pop();
      }
      nextDecreasingIndices[i] = decreasingIndices.isEmpty() ? -1 : decreasingIndices.peek();
      decreasingIndices.push(i);
    }

    long[] costSums = new long[n];
    Arrays.fill(costSums, Long.MAX_VALUE);
    costSums[0] = 0;
    for (int i = 0; i < costSums.length; ++i) {
      if (nextNonDecreasingIndices[i] != -1) {
        costSums[nextNonDecreasingIndices[i]] =
            Math.min(
                costSums[nextNonDecreasingIndices[i]],
                costSums[i] + costs[nextNonDecreasingIndices[i]]);
      }
      if (nextDecreasingIndices[i] != -1) {
        costSums[nextDecreasingIndices[i]] =
            Math.min(
                costSums[nextDecreasingIndices[i]], costSums[i] + costs[nextDecreasingIndices[i]]);
      }
    }

    return costSums[costSums.length - 1];
  }
}
