import java.util.stream.IntStream;

class Solution {
  public int maxPoints(int[][] points) {
    int result = 1;
    for (int i = 0; i < points.length; ++i) {
      for (int j = i + 1; j < points.length; ++j) {
        int i_ = i;
        int j_ = j;
        result =
            Math.max(
                result,
                (int)
                    IntStream.range(0, points.length)
                        .filter(
                            k ->
                                (points[i_][1] - points[k][1]) * (points[j_][0] - points[k][0])
                                    == (points[j_][1] - points[k][1])
                                        * (points[i_][0] - points[k][0]))
                        .count());
      }
    }

    return result;
  }
}
