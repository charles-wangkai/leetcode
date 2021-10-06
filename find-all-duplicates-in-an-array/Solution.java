import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> findDuplicates(int[] nums) {
    List<Integer> result = new ArrayList<>();
    for (int num : nums) {
      int original = Math.abs(num);

      if (nums[original - 1] <= -1) {
        result.add(original);
      } else {
        nums[original - 1] *= -1;
      }
    }

    return result;
  }
}
