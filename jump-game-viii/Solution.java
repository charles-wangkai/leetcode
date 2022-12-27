import java.util.Arrays;
import java.util.Stack;

class Solution {
  public long minCost(int[] nums, int[] costs) {
    int n = nums.length;

    int[] nextNonDecreasingIndices = new int[n];
    Stack<Integer> nonDecreasingIndices = new Stack<>();
    for (int i = nextNonDecreasingIndices.length - 1; i >= 0; --i) {
      while (!nonDecreasingIndices.empty() && nums[nonDecreasingIndices.peek()] < nums[i]) {
        nonDecreasingIndices.pop();
      }
      nextNonDecreasingIndices[i] = nonDecreasingIndices.empty() ? -1 : nonDecreasingIndices.peek();
      nonDecreasingIndices.push(i);
    }

    int[] nextDecreasingIndices = new int[n];
    Stack<Integer> decreasingIndices = new Stack<>();
    for (int i = nextDecreasingIndices.length - 1; i >= 0; --i) {
      while (!decreasingIndices.empty() && nums[decreasingIndices.peek()] >= nums[i]) {
        decreasingIndices.pop();
      }
      nextDecreasingIndices[i] = decreasingIndices.empty() ? -1 : decreasingIndices.peek();
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
