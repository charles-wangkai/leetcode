import java.util.HashSet;
import java.util.Set;

class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        Set<Integer> sums = new HashSet<>();
        sums.add(0);

        int result = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sums.contains(sum - target)) {
                ++result;
                sums.clear();
                sums.add(0);
                sum = 0;
            } else {
                sums.add(sum);
            }
        }

        return result;
    }
}