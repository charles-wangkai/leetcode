import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int findMinimumTime(int[][] tasks) {
    Arrays.sort(tasks, Comparator.comparing(task -> task[1]));

    boolean[] used =
        new boolean[Arrays.stream(tasks).mapToInt(task -> task[1]).max().getAsInt() + 1];
    for (int i = 0; i < tasks.length; ++i) {
      int rest = tasks[i][2];
      for (int j = tasks[i][0]; j <= tasks[i][1]; ++j) {
        if (used[j] || tasks[i][1] - j + 1 == rest) {
          used[j] = true;
          --rest;
        }
      }
    }

    return (int) IntStream.range(0, used.length).filter(i -> used[i]).count();
  }
}
