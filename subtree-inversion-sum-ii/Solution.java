// https://leetcode.com/problems/subtree-inversion-sum-ii/solutions/8307551/simple-dfs-solution-in-c-by-homerorr-wzeo/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
  public int subtreeInversionSum(int[][] edges, int[] nums, int k) {
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

    return Arrays.stream(search(nums, k, adjLists, -1, 0).normalMaxSums()).max().getAsInt();
  }

  Outcome search(int[] nums, int k, List<Integer>[] adjLists, int parent, int node) {
    int[] normalMaxSums = new int[k + 1];
    Arrays.fill(normalMaxSums, Integer.MIN_VALUE);

    int[] invertedMaxSums = new int[k + 1];
    Arrays.fill(invertedMaxSums, Integer.MIN_VALUE);

    normalMaxSums[k] = nums[node];
    invertedMaxSums[k] = -nums[node];

    normalMaxSums[0] = -nums[node];
    invertedMaxSums[0] = nums[node];

    for (int adj : adjLists[node]) {
      if (adj != parent) {
        Outcome outcome = search(nums, k, adjLists, node, adj);

        int[] nextNormalMaxSums = new int[k + 1];
        Arrays.fill(nextNormalMaxSums, Integer.MIN_VALUE);

        int[] nextInvertedMaxSums = new int[k + 1];
        Arrays.fill(nextInvertedMaxSums, Integer.MIN_VALUE);

        for (int i = 0; i <= k; ++i) {
          for (int j = 0; j <= k; ++j) {
            if (i + (j + 1) >= k) {
              int nextDistance = Math.min(i, j + 1);

              int childNormalContrib =
                  (i == 0) ? outcome.invertedMaxSums()[j] : outcome.normalMaxSums()[j];
              if (normalMaxSums[i] != Integer.MIN_VALUE
                  && childNormalContrib != Integer.MIN_VALUE) {
                nextNormalMaxSums[nextDistance] =
                    Math.max(
                        nextNormalMaxSums[nextDistance], normalMaxSums[i] + childNormalContrib);
              }

              int childInvertedContrib =
                  (i == 0) ? outcome.normalMaxSums()[j] : outcome.invertedMaxSums()[j];
              if (invertedMaxSums[i] != Integer.MIN_VALUE
                  && childInvertedContrib != Integer.MIN_VALUE) {
                nextInvertedMaxSums[nextDistance] =
                    Math.max(
                        nextInvertedMaxSums[nextDistance],
                        invertedMaxSums[i] + childInvertedContrib);
              }
            }
          }
        }

        normalMaxSums = nextNormalMaxSums;
        invertedMaxSums = nextInvertedMaxSums;
      }
    }

    return new Outcome(normalMaxSums, invertedMaxSums);
  }
}

record Outcome(int[] normalMaxSums, int[] invertedMaxSums) {}
