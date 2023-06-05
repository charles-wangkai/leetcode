import java.util.HashSet;
import java.util.Set;

class Solution {
  public long matrixSumQueries(int n, int[][] queries) {
    long result = 0;
    Set<Integer> seenRows = new HashSet<>();
    Set<Integer> seenCols = new HashSet<>();
    for (int i = queries.length - 1; i >= 0; --i) {
      if (queries[i][0] == 0) {
        if (!seenRows.contains(queries[i][1])) {
          result += queries[i][2] * (n - seenCols.size());
          seenRows.add(queries[i][1]);
        }
      } else {
        if (!seenCols.contains(queries[i][1])) {
          result += queries[i][2] * (n - seenRows.size());
          seenCols.add(queries[i][1]);
        }
      }
    }

    return result;
  }
}
