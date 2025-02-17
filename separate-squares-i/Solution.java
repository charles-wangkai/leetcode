import java.util.Arrays;

class Solution {
  static final double EPSILON = 1e-9;
  static final int ITERATION_NUM = 100;

  public double separateSquares(int[][] squares) {
    double lower = Arrays.stream(squares).mapToInt(square -> square[1]).min().getAsInt();
    double upper =
        Arrays.stream(squares).mapToInt(square -> square[1] + square[2]).max().getAsInt();
    for (int i = 0; i < ITERATION_NUM; ++i) {
      double middle = (lower + upper) / 2;
      if (computeAreaDiff(squares, middle) < EPSILON) {
        upper = middle;
      } else {
        lower = middle;
      }
    }

    return lower;
  }

  double computeAreaDiff(int[][] squares, double y) {
    return Arrays.stream(squares)
            .mapToDouble(square -> square[2] * Math.clamp(square[1] + square[2] - y, 0, square[2]))
            .sum()
        - Arrays.stream(squares)
            .mapToDouble(square -> square[2] * Math.clamp(y - square[1], 0, square[2]))
            .sum();
  }
}