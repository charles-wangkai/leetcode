import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Long> mergeAdjacent(int[] nums) {
    List<Long> result = new ArrayList<>();
    for (int num : nums) {
      result.add((long) num);

      while (result.size() >= 2
          && result.get(result.size() - 2).equals(result.get(result.size() - 1))) {
        result.add(result.removeLast() + result.removeLast());
      }
    }

    return result;
  }
}