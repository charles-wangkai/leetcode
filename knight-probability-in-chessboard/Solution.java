import java.util.HashMap;
import java.util.Map;

class Solution {
  static final int[] R_OFFSETS = {-2, -1, 1, 2, 2, 1, -1, -2};
  static final int[] C_OFFSETS = {1, 2, 2, 1, -1, -2, -2, -1};

  public double knightProbability(int n, int k, int row, int column) {
    Map<Cell, Double> cellToProb = Map.of(new Cell(row, column), 1.0);
    for (int i = 0; i < k; ++i) {
      Map<Cell, Double> nextCellToProb = new HashMap<>();
      for (Cell cell : cellToProb.keySet()) {
        for (int j = 0; j < R_OFFSETS.length; ++j) {
          int nextR = cell.r() + R_OFFSETS[j];
          int nextC = cell.c() + C_OFFSETS[j];
          if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {
            Cell nextCell = new Cell(nextR, nextC);
            nextCellToProb.put(
                nextCell,
                nextCellToProb.getOrDefault(nextCell, 0.0)
                    + cellToProb.get(cell) / R_OFFSETS.length);
          }
        }
      }

      cellToProb = nextCellToProb;
    }

    return cellToProb.values().stream().mapToDouble(Double::doubleValue).sum();
  }
}

record Cell(int r, int c) {}
