import java.util.ArrayList;
import java.util.List;

class Solution {
  static final int SIZE = 3;

  public int[][] resultGrid(int[][] image, int threshold) {
    int m = image.length;
    int n = image[0].length;

    Integer[][] averages = new Integer[m][n];
    for (int r = 0; r < m - (SIZE - 1); ++r) {
      for (int c = 0; c < n - (SIZE - 1); ++c) {
        averages[r][c] = computeAverage(image, threshold, r, c);
      }
    }

    int[][] result = new int[m][n];
    for (int r = 0; r < m; ++r) {
      for (int c = 0; c < n; ++c) {
        List<Integer> enclosed = new ArrayList<>();
        for (int dr = Math.max(-2, -r); dr <= 0; ++dr) {
          for (int dc = Math.max(-2, -c); dc <= 0; ++dc) {
            if (averages[r + dr][c + dc] != null) {
              enclosed.add(averages[r + dr][c + dc]);
            }
          }
        }

        result[r][c] =
            enclosed.isEmpty()
                ? image[r][c]
                : (enclosed.stream().mapToInt(Integer::intValue).sum() / enclosed.size());
      }
    }

    return result;
  }

  Integer computeAverage(int[][] image, int threshold, int minR, int minC) {
    for (int dr = 0; dr < SIZE; ++dr) {
      for (int dc = 0; dc < SIZE - 1; ++dc) {
        if (Math.abs(image[minR + dr][minC + dc] - image[minR + dr][minC + dc + 1]) > threshold) {
          return null;
        }
      }
    }
    for (int dc = 0; dc < SIZE; ++dc) {
      for (int dr = 0; dr < SIZE - 1; ++dr) {
        if (Math.abs(image[minR + dr][minC + dc] - image[minR + dr + 1][minC + dc]) > threshold) {
          return null;
        }
      }
    }

    int sum = 0;
    for (int dr = 0; dr < SIZE; ++dr) {
      for (int dc = 0; dc < SIZE; ++dc) {
        sum += image[minR + dr][minC + dc];
      }
    }

    return sum / (SIZE * SIZE);
  }
}