import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
  public List<Integer> intersection(int[][] nums) {
    List<Integer> result = Arrays.stream(nums[0]).boxed().collect(Collectors.toList());
    for (int i = 1; i < nums.length; ++i) {
      result.retainAll(Arrays.stream(nums[i]).boxed().collect(Collectors.toList()));
    }
    Collections.sort(result);

    return result;
  }
}