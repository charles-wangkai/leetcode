import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int longestBalanced(String s) {
    boolean[][] leftExists = new boolean[s.length()][2];
    for (int i = 0; i < leftExists.length; ++i) {
      for (int j = 0; j < 2; ++j) {
        leftExists[i][j] =
            ((i == 0) ? false : leftExists[i - 1][j]) || (i != 0 && s.charAt(i - 1) - '0' == j);
      }
    }

    boolean[][] rightExists = new boolean[s.length()][2];
    for (int i = rightExists.length - 1; i >= 0; --i) {
      for (int j = 0; j < 2; ++j) {
        rightExists[i][j] =
            ((i == rightExists.length - 1) ? false : rightExists[i + 1][j])
                || (i != rightExists.length - 1 && s.charAt(i + 1) - '0' == j);
      }
    }

    Map<Integer, List<Integer>> diffToLengths = new HashMap<>();
    diffToLengths.put(0, new ArrayList<>());
    diffToLengths.get(0).add(0);

    int result = 0;
    int diff = 0;
    for (int i = 0; i < s.length(); ++i) {
      diff += (s.charAt(i) == '1') ? 1 : -1;

      if (diffToLengths.containsKey(diff)) {
        result = Math.max(result, i + 1 - diffToLengths.get(diff).getFirst());
      }

      if (diffToLengths.containsKey(diff - 2)) {
        List<Integer> lengths = diffToLengths.get(diff - 2);
        if ((lengths.getFirst() != 0 && leftExists[lengths.getFirst() - 1][0])
            || rightExists[i][0]) {
          result = Math.max(result, i + 1 - lengths.getFirst());
        } else if (lengths.size() >= 2) {
          result = Math.max(result, i + 1 - lengths.get(1));
        }
      }

      if (diffToLengths.containsKey(diff + 2)) {
        List<Integer> lengths = diffToLengths.get(diff + 2);
        if ((lengths.getFirst() != 0 && leftExists[lengths.getFirst() - 1][1])
            || rightExists[i][1]) {
          result = Math.max(result, i + 1 - lengths.getFirst());
        } else if (lengths.size() >= 2) {
          result = Math.max(result, i + 1 - lengths.get(1));
        }
      }

      diffToLengths.putIfAbsent(diff, new ArrayList<>());
      diffToLengths.get(diff).add(i + 1);
    }

    return result;
  }
}