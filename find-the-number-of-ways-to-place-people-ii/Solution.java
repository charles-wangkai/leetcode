import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int numberOfPairs(int[][] points) {
    Map<Integer, Integer> xValueToCompressed =
        buildValueToCompressed(Arrays.stream(points).mapToInt(point -> point[0]).toArray());
    Map<Integer, Integer> yValueToCompressed =
        buildValueToCompressed(Arrays.stream(points).mapToInt(point -> -point[1]).toArray());

    int row = yValueToCompressed.size();
    int col = xValueToCompressed.size();

    boolean[][] grid = new boolean[row][col];
    for (int[] point : points) {
      grid[yValueToCompressed.get(-point[1])][xValueToCompressed.get(point[0])] = true;
    }

    int[][] prefixSums = new int[row][col];
    for (int r = 1; r < row; ++r) {
      for (int c = 1; c < col; ++c) {
        prefixSums[r][c] =
            prefixSums[r - 1][c]
                + prefixSums[r][c - 1]
                - prefixSums[r - 1][c - 1]
                + (grid[r][c] ? 1 : 0);
      }
    }

    int result = 0;
    for (int i = 0; i < points.length; ++i) {
      for (int j = 0; j < points.length; ++j) {
        if (j != i
            && points[j][0] >= points[i][0]
            && points[j][1] <= points[i][1]
            && computeRangeNum(
                    prefixSums,
                    yValueToCompressed.get(-points[i][1]),
                    yValueToCompressed.get(-points[j][1]),
                    xValueToCompressed.get(points[i][0]),
                    xValueToCompressed.get(points[j][0]))
                == 2) {
          ++result;
        }
      }
    }

    return result;
  }

  int computeRangeNum(int[][] prefixSums, int minR, int maxR, int minC, int maxC) {
    return prefixSums[maxR][maxC]
        - prefixSums[minR - 1][maxC]
        - prefixSums[maxR][minC - 1]
        + prefixSums[minR - 1][minC - 1];
  }

  Map<Integer, Integer> buildValueToCompressed(int[] values) {
    int[] sorted =
        IntStream.concat(IntStream.of(Integer.MIN_VALUE), Arrays.stream(values))
            .sorted()
            .distinct()
            .toArray();

    return IntStream.range(0, sorted.length)
        .boxed()
        .collect(Collectors.toMap(i -> sorted[i], i -> i));
  }
}