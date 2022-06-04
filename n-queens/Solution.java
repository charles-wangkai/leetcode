import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> solutions = new ArrayList<>();
    search(
        solutions, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], new int[n], 0);

    return solutions;
  }

  void search(
      List<List<String>> solutions,
      boolean[] columns,
      boolean[] diffs,
      boolean[] sums,
      int[] rows,
      int index) {
    int n = rows.length;

    if (index == n) {
      solutions.add(
          IntStream.range(0, n)
              .mapToObj(
                  r ->
                      IntStream.range(0, n)
                          .mapToObj(c -> (c == rows[r]) ? "Q" : ".")
                          .collect(Collectors.joining()))
              .collect(Collectors.toList()));

      return;
    }

    for (int i = 0; i < n; ++i) {
      if (!columns[i] && !diffs[index - i + (n - 1)] && !sums[index + i]) {
        columns[i] = true;
        diffs[index - i + (n - 1)] = true;
        sums[index + i] = true;

        rows[index] = i;
        search(solutions, columns, diffs, sums, rows, index + 1);

        sums[index + i] = false;
        diffs[index - i + (n - 1)] = false;
        columns[i] = false;
      }
    }
  }
}
