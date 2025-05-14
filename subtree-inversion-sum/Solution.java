import java.util.ArrayList;
import java.util.List;

class Solution {
  public long subtreeInversionSum(int[][] edges, int[] nums, int k) {
    int n = edges.length + 1;

    @SuppressWarnings("unchecked")
    List<Integer>[] adjLists = new List[n];
    for (int i = 0; i < adjLists.length; ++i) {
      adjLists[i] = new ArrayList<>();
    }
    for (int[] edge : edges) {
      adjLists[edge[0]].add(edge[1]);
      adjLists[edge[1]].add(edge[0]);
    }

    long[][] minSubtreeSums = new long[n][k];
    long[][] maxSubtreeSums = new long[n][k];

    search(nums, k, adjLists, minSubtreeSums, maxSubtreeSums, -1, 0);

    return maxSubtreeSums[0][0];
  }

  void search(
      int[] nums,
      int k,
      List<Integer>[] adjLists,
      long[][] minSubtreeSums,
      long[][] maxSubtreeSums,
      int parent,
      int node) {
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        search(nums, k, adjLists, minSubtreeSums, maxSubtreeSums, node, adj);
      }
    }

    for (int i = 0; i < k; ++i) {
      long minSubtreeSum = nums[node];
      long maxSubtreeSum = nums[node];
      for (int adj : adjLists[node]) {
        if (adj != parent) {
          minSubtreeSum += minSubtreeSums[adj][Math.max(0, i - 1)];
          maxSubtreeSum += maxSubtreeSums[adj][Math.max(0, i - 1)];
        }
      }

      minSubtreeSums[node][i] = minSubtreeSum;
      maxSubtreeSums[node][i] = maxSubtreeSum;
    }

    long invertedMinSubtreeSum = -nums[node];
    long invertedMaxSubtreeSum = -nums[node];
    for (int adj : adjLists[node]) {
      if (adj != parent) {
        invertedMinSubtreeSum += -maxSubtreeSums[adj][k - 1];
        invertedMaxSubtreeSum += -minSubtreeSums[adj][k - 1];
      }
    }

    minSubtreeSums[node][0] = Math.min(minSubtreeSums[node][0], invertedMinSubtreeSum);
    maxSubtreeSums[node][0] = Math.max(maxSubtreeSums[node][0], invertedMaxSubtreeSum);

    for (int i = k - 2; i >= 0; --i) {
      minSubtreeSums[node][i] = Math.min(minSubtreeSums[node][i], minSubtreeSums[node][i + 1]);
      maxSubtreeSums[node][i] = Math.max(maxSubtreeSums[node][i], maxSubtreeSums[node][i + 1]);
    }
  }
}