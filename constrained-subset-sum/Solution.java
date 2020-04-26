import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        SortedMap<Integer, Integer> sumToCount = new TreeMap<>();
        int[] sums = new int[nums.length];
        for (int i = 0; i < sums.length; ++i) {
            sums[i] = Math.max(0, sumToCount.isEmpty() ? 0 : sumToCount.lastKey()) + nums[i];

            sumToCount.put(sums[i], sumToCount.getOrDefault(sums[i], 0) + 1);

            if (i >= k) {
                sumToCount.put(sums[i - k], sumToCount.getOrDefault(sums[i - k], 0) - 1);
                sumToCount.remove(sums[i - k], 0);
            }
        }

        return Arrays.stream(sums).max().getAsInt();
    }
}