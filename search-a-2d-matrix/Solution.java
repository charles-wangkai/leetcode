import java.util.Arrays;

class Solution {
  public boolean searchMatrix(int[][] matrix, int target) {
    int rowIndex = findIndex(Arrays.stream(matrix).mapToInt(row -> row[0]).toArray(), target);
    int colIndex = findIndex(matrix[rowIndex], target);

    return matrix[rowIndex][colIndex] == target;
  }

  int findIndex(int[] a, int target) {
    int result = 0;
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
