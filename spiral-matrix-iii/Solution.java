import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int[] R_OFFSETS = {-1, 0, 1, 0};
  static final int[] C_OFFSETS = {0, 1, 0, -1};

  public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
    List<int[]> result = new ArrayList<>();
    int maxStep = 1;
    int maxStepCount = 0;
    int direction = 1;
    int step = 0;
    int r = rStart;
    int c = cStart;
    while (result.size() != rows * cols) {
      if (r >= 0 && r < rows && c >= 0 && c < cols) {
        result.add(new int[] {r, c});
      }

      if (step == maxStep) {
        step = 0;
        direction = (direction + 1) % R_OFFSETS.length;

        ++maxStepCount;
        if (maxStepCount == 2) {
          maxStepCount = 0;
          ++maxStep;
        }
      }

      ++step;
      r += R_OFFSETS[direction];
      c += C_OFFSETS[direction];
    }

    return result.toArray(int[][]::new);
  }
}
