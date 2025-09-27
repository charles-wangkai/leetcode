import java.util.stream.IntStream;

class Solution {
  public double largestTriangleArea(int[][] points) {
    double result = -1;
    for (int i = 0; i < points.length; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        for (int k = j + 1; k < points.length; ++k) {
          result = Math.max(result, computeArea(points[i], points[j], points[k]));
        }
      }
    }

    return result;
  }

  double computeArea(int[] p1, int[] p2, int[] p3) {
    int[][] triangle = {p1, p2, p3};

    return Math.abs(
            IntStream.range(0, triangle.length)
                .map(
                    i ->
                        triangle[i][0] * triangle[(i + 1) % triangle.length][1]
                            - triangle[(i + 1) % triangle.length][0] * triangle[i][1])
                .sum())
        / 2.0;
  }
}
