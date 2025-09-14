import java.util.Arrays;

class Solution {
  public int earliestTime(int[][] tasks) {
    return Arrays.stream(tasks).mapToInt(task -> task[0] + task[1]).min().getAsInt();
  }
}