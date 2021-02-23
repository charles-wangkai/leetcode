class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    int row = matrix.length;
    int col = matrix[0].length;

    int r = row - 1;
    int c = 0;
    while (r >= 0 && c < col) {
      if (matrix[r][c] == target) {
        return true;
      } else if (matrix[r][c] < target) {
        ++c;
      } else {
        --r;
      }
    }

    return false;
  }
}
