import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean canPartitionGrid(int[][] grid) {
    return canCut(grid) || canCut(transpose(grid));
  }

  int[][] transpose(int[][] m) {
    int[][] result = new int[m[0].length][m.length];
    for (int r = 0; r < result.length; ++r) {
      for (int c = 0; c < result[0].length; ++c) {
        result[r][c] = m[c][r];
      }
    }

    return result;
  }

  boolean canCut(int[][] m) {
    int row = m.length;
    int col = m[0].length;

    Map<Long, Integer> valueToMinR = new HashMap<>();
    Map<Long, Integer> valueToMaxR = new HashMap<>();
    for (int r = 0; r < row; ++r) {
      for (int c = 0; c < col; ++c) {
        if (!valueToMinR.containsKey((long) m[r][c])) {
          valueToMinR.put((long) m[r][c], r);
        }
        valueToMaxR.put((long) m[r][c], r);
      }
    }

    long[] rowSums =
        Arrays.stream(m).mapToLong(line -> Arrays.stream(line).asLongStream().sum()).toArray();

    long total = Arrays.stream(rowSums).sum();
    long upSum = 0;
    for (int r = 0; r < row - 1; ++r) {
      upSum += rowSums[r];
      long downSum = total - upSum;
      long diff = Math.abs(upSum - downSum);
      if (diff == 0) {
        return true;
      }

      if (upSum > downSum) {
        if (r == 0) {
          if (m[0][0] == diff || m[0][col - 1] == diff) {
            return true;
          }
        } else if (col == 1) {
          if (m[0][0] == diff || m[r][0] == diff) {
            return true;
          }
        } else if (valueToMinR.getOrDefault(diff, Integer.MAX_VALUE) <= r) {
          return true;
        }
      } else if (r == row - 2) {
        if (m[row - 1][0] == diff || m[row - 1][col - 1] == diff) {
          return true;
        }
      } else if (col == 1) {
        if (m[row - 1][0] == diff || m[r + 1][0] == diff) {
          return true;
        }
      } else if (valueToMaxR.getOrDefault(diff, Integer.MIN_VALUE) > r) {
        return true;
      }
    }

    return false;
  }
}
