import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
  public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    Arrays.sort(slots1, Comparator.comparing(slot -> slot[0]));
    Arrays.sort(slots2, Comparator.comparing(slot -> slot[0]));

    int index1 = 0;
    int index2 = 0;
    while (index1 != slots1.length && index2 != slots2.length) {
      int[] slot1 = slots1[index1];
      int[] slot2 = slots2[index2];

      int beginTime = Math.max(slot1[0], slot2[0]);
      int endTime = Math.min(slot1[1], slot2[1]);
      if (endTime - beginTime >= duration) {
        return List.of(beginTime, beginTime + duration);
      }

      if (slot1[1] <= slot2[1]) {
        ++index1;
      } else {
        ++index2;
      }
    }

    return List.of();
  }
}
