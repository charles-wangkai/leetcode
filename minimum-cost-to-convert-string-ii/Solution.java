import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
  public long minimumCost(
      String source, String target, String[] original, String[] changed, int[] cost) {
    String[] strs =
        Stream.concat(Arrays.stream(original), Arrays.stream(changed))
            .distinct()
            .toArray(String[]::new);
    Map<String, Integer> strToIndex =
        IntStream.range(0, strs.length).boxed().collect(Collectors.toMap(i -> strs[i], i -> i));

    int[] lengths = Arrays.stream(original).mapToInt(String::length).distinct().sorted().toArray();

    int[][] distances = buildDistances(strToIndex, original, changed, cost);

    long[] dp = new long[source.length() + 1];
    Arrays.fill(dp, Long.MAX_VALUE);
    dp[0] = 0;

    for (int i = 1; i < dp.length; ++i) {
      if (source.charAt(i - 1) == target.charAt(i - 1)) {
        dp[i] = Math.min(dp[i], dp[i - 1]);
      }

      for (int length : lengths) {
        if (length > i) {
          break;
        }

        String from = source.substring(i - length, i);
        String to = target.substring(i - length, i);
        if (dp[i - length] != Long.MAX_VALUE
            && strToIndex.containsKey(from)
            && strToIndex.containsKey(to)
            && distances[strToIndex.get(from)][strToIndex.get(to)] != Integer.MAX_VALUE) {
          dp[i] =
              Math.min(dp[i], dp[i - length] + distances[strToIndex.get(from)][strToIndex.get(to)]);
        }
      }
    }

    return (dp[dp.length - 1] == Long.MAX_VALUE) ? -1 : dp[dp.length - 1];
  }

  int[][] buildDistances(
      Map<String, Integer> strToIndex, String[] original, String[] changed, int[] cost) {
    int[][] distances = new int[strToIndex.size()][strToIndex.size()];
    for (int i = 0; i < strToIndex.size(); ++i) {
      for (int j = 0; j < strToIndex.size(); ++j) {
        distances[i][j] = (j == i) ? 0 : Integer.MAX_VALUE;
      }
    }
    for (int i = 0; i < original.length; ++i) {
      distances[strToIndex.get(original[i])][strToIndex.get(changed[i])] =
          Math.min(distances[strToIndex.get(original[i])][strToIndex.get(changed[i])], cost[i]);
    }

    for (int k = 0; k < strToIndex.size(); ++k) {
      for (int i = 0; i < strToIndex.size(); ++i) {
        for (int j = 0; j < strToIndex.size(); ++j) {
          if (distances[i][k] != Integer.MAX_VALUE && distances[k][j] != Integer.MAX_VALUE) {
            distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
          }
        }
      }
    }

    return distances;
  }
}