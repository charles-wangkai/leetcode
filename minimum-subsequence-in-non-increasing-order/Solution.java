import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);

        int total = Arrays.stream(nums).sum();
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        for (int i = nums.length - 1;; --i) {
            result.add(nums[i]);
            sum += nums[i];

            if (sum * 2 > total) {
                return result;
            }
        }
    }
}