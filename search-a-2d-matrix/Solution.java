import java.util.stream.IntStream;

class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    int row = matrix.length;
    if (row == 0) {
      return false;
    }
    int col = matrix[0].length;
    if (col == 0 || target < matrix[0][0]) {
      return false;
    }

    int rowIndex = findIndex(IntStream.range(0, row).map(r -> matrix[r][0]).toArray(), target);
    int colIndex = findIndex(matrix[rowIndex], target);

    return matrix[rowIndex][colIndex] == target;
  }

  int findIndex(int[] a, int target) {
    int result = -1;
    int lower = 0;
    int upper = a.length - 1;
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (a[middle] <= target) {
        result = middle;
        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }
}
