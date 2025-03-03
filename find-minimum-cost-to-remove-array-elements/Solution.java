import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minCost(int[] nums) {
    return search(new HashMap<>(), nums, nums[0], 1);
  }

  int search(Map<State, Integer> cache, int[] nums, int prev, int index) {
    if (index == nums.length) {
      return prev;
    }
    if (index == nums.length - 1) {
      return Math.max(prev, nums[index]);
    }

    State state = new State(prev, index);
    if (!cache.containsKey(state)) {
      cache.put(
          state,
          Math.min(
              Math.min(
                  Math.max(nums[index], nums[index + 1]) + search(cache, nums, prev, index + 2),
                  Math.max(prev, nums[index + 1]) + search(cache, nums, nums[index], index + 2)),
              Math.max(prev, nums[index]) + search(cache, nums, nums[index + 1], index + 2)));
    }

    return cache.get(state);
  }
}

record State(int prev, int index) {}
