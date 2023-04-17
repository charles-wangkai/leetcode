import java.util.Arrays;

class Solution {
  public int[] rowAndMaximumOnes(int[][] mat) {
    int rowIndex = -1;
    int maxOneNum = -1;
    for (int r = 0; r < mat.length; ++r) {
      int oneNum = (int) Arrays.stream(mat[r]).filter(x -> x == 1).count();
      if (oneNum > maxOneNum) {
        rowIndex = r;
        maxOneNum = oneNum;
      }
    }

    return new int[] {rowIndex, maxOneNum};
  }
}
