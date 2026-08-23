import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public List<List<Integer>> findDisappearedNumbers(int[] nums, int lower, int upper) {
    Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    List<List<Integer>> result = new ArrayList<>();
    for (int value = lower; value <= upper; ++value) {
      if (!set.contains(value)) {
        if (!result.isEmpty() && result.getLast().get(1) + 1 == value) {
          result.getLast().set(1, value);
        } else {
          List<Integer> range = new ArrayList<>();
          range.add(value);
          range.add(value);

          result.add(range);
        }
      }
    }

    return result;
  }
}