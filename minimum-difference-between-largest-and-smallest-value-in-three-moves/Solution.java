import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
    public int minDifference(int[] nums) {
        if (nums.length <= 4) {
            return 0;
        }

        nums = Arrays.stream(nums).boxed().sorted().mapToInt(x -> x).toArray();

        int[] nums_ = nums;
        return IntStream.rangeClosed(0, 3).map(i -> nums_[i + nums_.length - 4] - nums_[i]).min().getAsInt();
    }
}