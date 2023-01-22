import java.util.Arrays;
import java.util.Comparator;

class Solution {
  public int[][] sortTheStudents(int[][] score, int k) {
    return Arrays.stream(score)
        .sorted(Comparator.comparing((int[] line) -> line[k]).reversed())
        .toArray(int[][]::new);
  }
}
