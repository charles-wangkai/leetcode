import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minSubarray(int[] nums, int p) {
        int targetRemainder = (int) (Arrays.stream(nums).asLongStream().sum() % p);
        if (targetRemainder == 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;
        Map<Integer, Integer> remainderToLastIndex = new HashMap<>();
        remainderToLastIndex.put(0, -1);
        int remainder = 0;
        for (int i = 0; i < nums.length; ++i) {
            remainder = (remainder + nums[i]) % p;
            int otherRemainder = (remainder - targetRemainder + p) % p;
            if (remainderToLastIndex.containsKey(otherRemainder)) {
                result = Math.min(result, i - remainderToLastIndex.get(otherRemainder));
            }

            remainderToLastIndex.put(remainder, i);
        }

        return (result == nums.length) ? -1 : result;
    }
}