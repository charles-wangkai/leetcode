// https://leetcode.com/problems/minimum-cost-to-divide-array-into-subarrays/solutions/6593317/trick-is-see-from-how-many-elems-of-cost-arr-a-ele-of-nums-is-multiplied-2d-dp-explained-well/

class Solution {
  public long minimumCost(int[] nums, int[] cost, int k) {
    int[] valuePrefixSums = new int[nums.length];
    for (int i = 0; i < valuePrefixSums.length; ++i) {
      valuePrefixSums[i] = ((i == 0) ? 0 : valuePrefixSums[i - 1]) + nums[i];
    }

    int[] costSuffixSums = new int[cost.length];
    for (int i = costSuffixSums.length - 1; i >= 0; --i) {
      costSuffixSums[i] = ((i == costSuffixSums.length - 1) ? 0 : costSuffixSums[i + 1]) + cost[i];
    }

    return search(new Long[nums.length][nums.length], k, valuePrefixSums, costSuffixSums, 0, 0);
  }

  long search(
      Long[][] cache,
      int k,
      int[] valuePrefixSums,
      int[] costSuffixSums,
      int beginIndex,
      int endIndex) {
    if (endIndex == valuePrefixSums.length) {
      return (beginIndex == endIndex) ? 0 : Long.MAX_VALUE;
    }

    if (cache[beginIndex][endIndex] == null) {
      cache[beginIndex][endIndex] =
          Math.min(
              search(cache, k, valuePrefixSums, costSuffixSums, beginIndex, endIndex + 1),
              (long)
                          ((valuePrefixSums[endIndex]
                                  - ((beginIndex == 0) ? 0 : valuePrefixSums[beginIndex - 1]))
                              + k)
                      * costSuffixSums[beginIndex]
                  + search(cache, k, valuePrefixSums, costSuffixSums, endIndex + 1, endIndex + 1));
    }

    return cache[beginIndex][endIndex];
  }
}
