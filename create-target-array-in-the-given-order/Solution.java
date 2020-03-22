import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new ArrayList<>();
        for (int i = 0; i < nums.length; ++i) {
            target.add(index[i], nums[i]);
        }

        return target.stream().mapToInt(x -> x).toArray();
    }
}