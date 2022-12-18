import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int[] cycleLengthQueries(int n, int[][] queries) {
    return Arrays.stream(queries)
        .mapToInt(
            query -> {
              int[] path1 = buildPath(query[0]);
              int[] path2 = buildPath(query[1]);

              int index1 = path1.length - 1;
              int index2 = path2.length - 1;
              while (index1 != 0 && index2 != 0 && path1[index1 - 1] == path2[index2 - 1]) {
                --index1;
                --index2;
              }

              return index1 + index2 + 1;
            })
        .toArray();
  }

  int[] buildPath(int value) {
    return IntStream.iterate(value, x -> x / 2).takeWhile(x -> x >= 1).toArray();
  }
}
