import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
    Set<Integer> occupied = new HashSet<>();
    for (int num : nums) {
      occupied.add(num);
    }

    for (int i = 0; i < moveFrom.length; ++i) {
      occupied.remove(moveFrom[i]);
      occupied.add(moveTo[i]);
    }

    return occupied.stream().sorted().toList();
  }
}
