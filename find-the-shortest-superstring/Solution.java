import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.stream.IntStream;

class Solution {
  public String shortestSuperstring(String[] words) {
    int n = words.length;

    int[][] graph = new int[n][n];
    for (int i = 0; i < n; ++i) {
      for (int j = 0; j < n; ++j) {
        graph[i][j] = computeOverlap(words[i], words[j]);
        graph[j][i] = computeOverlap(words[j], words[i]);
      }
    }

    int[][] overlaps = new int[1 << n][n];
    int[][] paths = new int[1 << n][n];
    for (int i = 1; i < (1 << n); ++i) {
      Arrays.fill(overlaps[i], -1);

      for (int j = 0; j < n; ++j) {
        if ((i & (1 << j)) != 0) {
          int prev = i - (1 << j);

          if (prev == 0) {
            overlaps[i][j] = 0;
          } else {
            for (int k = 0; k < n; ++k) {
              if (overlaps[prev][k] != -1 && overlaps[prev][k] + graph[k][j] > overlaps[i][j]) {
                overlaps[i][j] = overlaps[prev][k] + graph[k][j];
                paths[i][j] = k;
              }
            }
          }
        }
      }
    }

    int last =
        IntStream.range(0, n)
            .boxed()
            .max(Comparator.comparing(j -> overlaps[(1 << n) - 1][j]))
            .get();

    int cur = (1 << n) - 1;
    Stack<Integer> stack = new Stack<>();
    while (cur != 0) {
      stack.push(last);

      int nextLast = paths[cur][last];
      cur -= 1 << last;
      last = nextLast;
    }

    StringBuilder result = new StringBuilder();
    int prev = -1;
    while (!stack.empty()) {
      int current = stack.pop();

      if (prev == -1) {
        result.append(words[current]);
      } else {
        result.append(words[current].substring(graph[prev][current]));
      }

      prev = current;
    }

    return result.toString();
  }

  static int computeOverlap(String a, String b) {
    for (int beginIndex = 1; ; ++beginIndex) {
      if (b.startsWith(a.substring(beginIndex))) {
        return a.length() - beginIndex;
      }
    }
  }
}
