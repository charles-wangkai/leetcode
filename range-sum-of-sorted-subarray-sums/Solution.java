import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> sums = new ArrayList<>();
        for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
            int sum = 0;
            for (int endIndex = beginIndex; endIndex < nums.length; ++endIndex) {
                sum += nums[endIndex];
                sums.add(sum);
            }
        }

        Collections.sort(sums);

        return (int) (IntStream.rangeClosed(left - 1, right - 1).map(sums::get).asLongStream().sum() % 1_000_000_007);
    }
}