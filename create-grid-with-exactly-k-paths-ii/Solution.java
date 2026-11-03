import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int ROW_LIMIT = 25;
  static final int COL_LIMIT = 25;
  static final int PATH_NUM_LIMIT = 1000;

  static Rectangle[][] transitions = new Rectangle[ROW_LIMIT + 1][PATH_NUM_LIMIT + 1];

  static {
    precompute();
  }

  static void precompute() {
    for (int row = 0; row <= ROW_LIMIT; ++row) {
      for (int pathNum = 0; pathNum <= PATH_NUM_LIMIT; ++pathNum) {
        if ((row == 0 && pathNum == 0) || transitions[row][pathNum] != null) {
          for (int nextRow = row + 2; nextRow <= ROW_LIMIT; ++nextRow) {
            for (int width = 1; width <= COL_LIMIT - 2; ++width) {
              int nextPathNum = pathNum + C((nextRow - row - 2) + (width - 1), nextRow - row - 2);
              if (nextPathNum > PATH_NUM_LIMIT) {
                break;
              }

              transitions[nextRow][nextPathNum] = new Rectangle(nextRow - row - 1, width);
            }
          }
        }
      }
    }
  }

  static int C(int n, int r) {
    int result = 1;
    for (int i = 0; i < r; ++i) {
      result = result * (n - i) / (i + 1);
    }

    return result;
  }

  public List<String> createGrid(int k) {
    char[][] result = new char[ROW_LIMIT][COL_LIMIT];
    for (int r = 0; r < result.length; ++r) {
      Arrays.fill(result[r], '#');
    }

    int row =
        IntStream.rangeClosed(0, ROW_LIMIT)
            .filter(r -> transitions[r][k] != null)
            .findAny()
            .getAsInt();
    int pathNum = k;

    while (transitions[row][pathNum] != null) {
      Rectangle transition = transitions[row][pathNum];

      for (int r = 0; r < row - transition.height(); ++r) {
        result[r][0] = '.';
      }

      for (int r = row - transition.height(); r < row; ++r) {
        for (int c = 0; c < transition.width(); ++c) {
          result[r][c] = '.';
        }
      }

      for (int c = transition.width(); c < COL_LIMIT; ++c) {
        result[row - 1][c] = '.';
      }

      for (int r = row; r < ROW_LIMIT; ++r) {
        result[r][COL_LIMIT - 1] = '.';
      }

      row -= transition.height() + 1;
      pathNum -= C((transition.height() - 1) + (transition.width() - 1), transition.height() - 1);
    }

    return Arrays.stream(result).map(String::valueOf).toList();
  }
}

record Rectangle(int height, int width) {}
