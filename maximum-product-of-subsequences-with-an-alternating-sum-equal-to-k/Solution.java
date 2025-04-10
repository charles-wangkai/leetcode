// https://leetcode.com/problems/maximum-product-of-subsequences-with-an-alternating-sum-equal-to-k/solutions/6620515/c-dp-beginner-friendly-clean-intuitive-solution/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int maxProduct(int[] nums, int k, int limit) {
    if (Math.abs(k) > Arrays.stream(nums).sum()) {
      return -1;
    }

    return search(new HashMap<>(), nums, k, limit, 0, 0, 1, null);
  }

  int search(
      Map<State, Integer> cache,
      int[] nums,
      int k,
      int limit,
      int index,
      int sum,
      int product,
      Boolean addOrMinus) {
    if (index == nums.length) {
      return (addOrMinus != null && sum == k && product <= limit) ? product : -1;
    }

    State state = new State(index, sum, product, addOrMinus);
    if (!cache.containsKey(state)) {
      int result = search(cache, nums, k, limit, index + 1, sum, product, addOrMinus);
      if (addOrMinus == null || addOrMinus) {
        result =
            Math.max(
                result,
                search(
                    cache,
                    nums,
                    k,
                    limit,
                    index + 1,
                    sum + nums[index],
                    Math.min(limit + 1, product * nums[index]),
                    false));
      } else {
        result =
            Math.max(
                result,
                search(
                    cache,
                    nums,
                    k,
                    limit,
                    index + 1,
                    sum - nums[index],
                    Math.min(limit + 1, product * nums[index]),
                    true));
      }

      cache.put(state, result);
    }

    return cache.get(state);
  }
}

record State(int index, int sum, int product, Boolean addOrMinus) {}
