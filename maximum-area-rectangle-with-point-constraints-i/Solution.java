import java.util.stream.IntStream;

class Solution {
  public int maxRectangleArea(int[][] points) {
    int result = -1;
    for (int a = 0; a < points.length; ++a) {
      for (int b = 0; b < points.length; ++b) {
        for (int c = 0; c < points.length; ++c) {
          for (int d = 0; d < points.length; ++d) {
            int a_ = a;
            int b_ = b;
            int c_ = c;
            int d_ = d;
            if (points[b][1] == points[a][1]
                && points[b][0] > points[a][0]
                && points[c][0] == points[b][0]
                && points[c][1] > points[b][1]
                && points[d][1] == points[c][1]
                && points[d][0] == points[a][0]
                && IntStream.range(0, points.length)
                    .allMatch(
                        i ->
                            i == a_
                                || i == b_
                                || i == c_
                                || i == d_
                                || points[i][0] < points[a_][0]
                                || points[i][0] > points[b_][0]
                                || points[i][1] < points[a_][1]
                                || points[i][1] > points[c_][1])) {
              result =
                  Math.max(result, (points[b][0] - points[a][0]) * (points[c][1] - points[b][1]));
            }
          }
        }
      }
    }

    return result;
  }
}