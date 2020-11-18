import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
    int stateNum = IntStream.range(0, n).map(i -> 3).reduce((x, y) -> x * y).getAsInt();

    int[][][][] hapinesses = new int[m + 1][stateNum][introvertsCount + 1][extrovertsCount + 1];
    for (int i = 0; i <= m; ++i) {
      for (int j = 0; j < stateNum; ++j) {
        for (int p = 0; p <= introvertsCount; ++p) {
          for (int q = 0; q <= extrovertsCount; ++q) {
            hapinesses[i][j][p][q] = -1;
          }
        }
      }
    }
    hapinesses[0][0][introvertsCount][extrovertsCount] = 0;

    for (int i = 1; i <= m; ++i) {
      for (int j = 0; j < stateNum; ++j) {
        int[] currRow = decode(n, j);
        int currIntrovert = count(currRow, 1);
        int currExtrovert = count(currRow, 2);

        for (int p = 0; p <= introvertsCount; ++p) {
          int prevP = p + currIntrovert;
          if (prevP > introvertsCount) {
            continue;
          }

          for (int q = 0; q <= extrovertsCount; ++q) {
            int prevQ = q + currExtrovert;
            if (prevQ > extrovertsCount) {
              continue;
            }

            for (int prevState = 0; prevState < stateNum; ++prevState) {
              int[] prevRow = decode(n, prevState);
              if (hapinesses[i - 1][prevState][prevP][prevQ] == -1) {
                continue;
              }

              hapinesses[i][j][p][q] =
                  Math.max(
                      hapinesses[i][j][p][q],
                      hapinesses[i - 1][prevState][prevP][prevQ] + computeDelta(prevRow, currRow));
            }
          }
        }
      }
    }

    int result = 0;
    for (int j = 0; j < stateNum; ++j) {
      for (int p = 0; p <= introvertsCount; ++p) {
        for (int q = 0; q <= extrovertsCount; ++q) {
          result = Math.max(result, hapinesses[m][j][p][q]);
        }
      }
    }

    return result;
  }

  int[] decode(int size, int state) {
    int[] result = new int[size];
    for (int i = 0; i < result.length; ++i) {
      result[i] = state % 3;
      state /= 3;
    }

    return result;
  }

  int count(int[] row, int target) {
    return (int) Arrays.stream(row).filter(x -> x == target).count();
  }

  int computeDelta(int[] prevRow, int[] currRow) {
    int delta = 0;
    for (int i = 0; i < prevRow.length; ++i) {
      if (currRow[i] != 0) {
        if (prevRow[i] == 1) {
          delta -= 30;
        } else if (prevRow[i] == 2) {
          delta += 20;
        }
      }
    }
    for (int i = 0; i < currRow.length; ++i) {
      int adjCount = 0;
      if (i != 0 && currRow[i - 1] != 0) {
        ++adjCount;
      }
      if (i + 1 != currRow.length && currRow[i + 1] != 0) {
        ++adjCount;
      }
      if (prevRow[i] != 0) {
        ++adjCount;
      }

      if (currRow[i] == 1) {
        delta += 120 - 30 * adjCount;
      } else if (currRow[i] == 2) {
        delta += 40 + 20 * adjCount;
      }
    }

    return delta;
  }
}
