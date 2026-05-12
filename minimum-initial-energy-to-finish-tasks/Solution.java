import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int minimumEffort(int[][] tasks) {
    int[][] sortedTasks =
        Arrays.stream(tasks)
            .sorted(Comparator.<int[], Integer>comparing(task -> task[1] - task[0]).reversed())
            .toArray(int[][]::new);

    int result = 0;
    int rest = 0;
    for (int[] task : sortedTasks) {
      int delta = Math.max(0, task[1] - rest);

      result += delta;
      rest += delta - task[0];
    }

    return result;
  }
}
