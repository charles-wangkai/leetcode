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
      if (Arrays.stream(squares)
                  .mapToDouble(
                      square ->
                          square[2] * Math.clamp(square[1] + square[2] - middle, 0, square[2]))
                  .sum()
              - Arrays.stream(squares)
                  .mapToDouble(square -> square[2] * Math.clamp(middle - square[1], 0, square[2]))
                  .sum()
          < EPSILON) {
        upper = middle;
      } else {
        lower = middle;
      }
    }

    return lower;
  }
}