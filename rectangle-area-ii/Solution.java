import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int rectangleArea(int[][] rectangles) {
    int[] xValues =
        Arrays.stream(rectangles)
            .flatMapToInt(rectangle -> IntStream.of(rectangle[0], rectangle[2]))
            .distinct()
            .sorted()
            .toArray();
    int[] yValues =
        Arrays.stream(rectangles)
            .flatMapToInt(rectangle -> IntStream.of(rectangle[1], rectangle[3]))
            .distinct()
            .sorted()
            .toArray();

    boolean[][] fills = new boolean[xValues.length - 1][yValues.length - 1];
    for (int[] rectangle : rectangles) {
      int xMinIndex = findIndex(xValues, rectangle[0]);
      int xMaxIndex = findIndex(xValues, rectangle[2]);
      int yMinIndex = findIndex(yValues, rectangle[1]);
      int yMaxIndex = findIndex(yValues, rectangle[3]);

      for (int xIndex = xMinIndex; xIndex < xMaxIndex; ++xIndex) {
        for (int yIndex = yMinIndex; yIndex < yMaxIndex; ++yIndex) {
          fills[xIndex][yIndex] = true;
        }
      }
    }

    int area = 0;
    for (int i = 0; i < fills.length; ++i) {
      for (int j = 0; j < fills[0].length; ++j) {
        if (fills[i][j]) {
          area =
              addMod(area, multiplyMod(xValues[i + 1] - xValues[i], yValues[j + 1] - yValues[j]));
        }
      }
    }

    return area;
  }

  int findIndex(int[] values, int target) {
    return Arrays.binarySearch(values, target);
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
