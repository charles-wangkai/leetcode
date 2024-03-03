import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class Solution {
  public int[] resultArray(int[] nums) {
    List<Integer> values1 = new ArrayList<>();
    values1.add(nums[0]);
    List<Integer> values2 = new ArrayList<>();
    values2.add(nums[1]);

    for (int i = 2; i < nums.length; ++i) {
      ((values1.get(values1.size() - 1) > values2.get(values2.size() - 1)) ? values1 : values2)
          .add(nums[i]);
    }

    return Stream.concat(values1.stream(), values2.stream()).mapToInt(Integer::intValue).toArray();
  }
}