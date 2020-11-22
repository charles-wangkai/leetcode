import java.util.Arrays;

class Solution {
  public int minimumEffort(int[][] tasks) {
    int[][] sortedTasks =
        Arrays.stream(tasks)
            .sorted((t1, t2) -> -Integer.compare(t1[1] - t1[0], t2[1] - t2[0]))
            .toArray(int[][]::new);

    int result = 0;
    int remain = 0;
    for (int[] task : sortedTasks) {
      int delta = Math.max(0, task[1] - remain);

      result += delta;
      remain = remain + delta - task[0];
    }

    return result;
  }
}
