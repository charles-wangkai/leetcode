import java.util.stream.IntStream;

class Solution {
  public int numberOfPairs(int[][] points) {
    int n = points.length;

    int result = 0;
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        if (j != i) {
          int i_ = i;
          int j_ = j;
          if (points[j][0] >= points[i][0]
              && points[j][1] <= points[i][1]
              && IntStream.range(0, n)
                  .allMatch(
                      k ->
                          k == i_
                              || k == j_
                              || !(points[k][0] >= points[i_][0]
                                  && points[k][0] <= points[j_][0]
                                  && points[k][1] >= points[j_][1]
                                  && points[k][1] <= points[i_][1]))) {
            ++result;
          }
        }
      }
    }

    return result;
  }
}