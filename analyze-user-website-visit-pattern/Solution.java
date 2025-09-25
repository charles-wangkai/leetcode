import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
    int[] sortedIndices =
        IntStream.range(0, username.length)
            .boxed()
            .sorted(Comparator.comparing(i -> timestamp[i]))
            .mapToInt(Integer::intValue)
            .toArray();

    Map<String, List<Integer>> usernameToIndices = new HashMap<>();
    for (int index : sortedIndices) {
      usernameToIndices.putIfAbsent(username[index], new ArrayList<>());
      usernameToIndices.get(username[index]).add(index);
    }

    List<Set<List<String>>> patternSets =
        usernameToIndices.values().stream()
            .map(
                indices -> {
                  Set<List<String>> patternSet = new HashSet<>();
                  for (int i = 0; i < indices.size(); ++i) {
                    for (int j = i + 1; j < indices.size(); ++j) {
                      for (int k = j + 1; k < indices.size(); ++k) {
                        patternSet.add(
                            List.of(
                                website[indices.get(i)],
                                website[indices.get(j)],
                                website[indices.get(k)]));
                      }
                    }
                  }

                  return patternSet;
                })
            .toList();

    Map<List<String>, Integer> patternToCount = new HashMap<>();
    for (Set<List<String>> patternSet : patternSets) {
      for (List<String> pattern : patternSet) {
        patternToCount.put(pattern, patternToCount.getOrDefault(pattern, 0) + 1);
      }
    }

    int maxCount = patternToCount.values().stream().mapToInt(Integer::intValue).max().getAsInt();

    return patternToCount.keySet().stream()
        .filter(pattern -> patternToCount.get(pattern) == maxCount)
        .min(
            (pattern1, pattern2) ->
                IntStream.range(0, pattern1.size())
                    .map(i -> pattern1.get(i).compareTo(pattern2.get(i)))
                    .filter(cmp -> cmp != 0)
                    .findFirst()
                    .getAsInt())
        .get();
  }
}
