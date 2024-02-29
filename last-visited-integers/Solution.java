import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> lastVisitedIntegers(int[] nums) {
    List<Integer> result = new ArrayList<>();
    List<Integer> values = new ArrayList<>();
    int prevCount = 0;
    for (int num : nums) {
      if (num == -1) {
        ++prevCount;
        result.add((prevCount <= values.size()) ? values.get(prevCount - 1) : -1);
      } else {
        prevCount = 0;
        values.add(0, num);
      }
    }

    return result;
  }
}
