import java.util.HashMap;
import java.util.Map;

public class Solution {
  public int[][] multiply(int[][] A, int[][] B) {
    int[][] result = new int[A.length][B[0].length];

    Map<Integer, Map<Integer, Integer>> sparseMatrixA = buildSparseMatrix(A);
    Map<Integer, Map<Integer, Integer>> sparseMatrixB = buildSparseMatrix(B);

    for (int rowA : sparseMatrixA.keySet()) {
      for (int colA : sparseMatrixA.get(rowA).keySet()) {
        if (sparseMatrixB.containsKey(colA)) {
          for (int colB : sparseMatrixB.get(colA).keySet()) {
            result[rowA][colB] +=
                sparseMatrixA.get(rowA).get(colA) * sparseMatrixB.get(colA).get(colB);
          }
        }
      }
    }

    return result;
  }

  Map<Integer, Map<Integer, Integer>> buildSparseMatrix(int[][] m) {
    Map<Integer, Map<Integer, Integer>> sparseMatrix = new HashMap<>();
    for (int row = 0; row < m.length; row++) {
      for (int col = 0; col < m[0].length; col++) {
        if (m[row][col] != 0) {
          if (!sparseMatrix.containsKey(row)) {
            sparseMatrix.put(row, new HashMap<>());
          }
          sparseMatrix.get(row).put(col, m[row][col]);
        }
      }
    }
    return sparseMatrix;
  }
}
