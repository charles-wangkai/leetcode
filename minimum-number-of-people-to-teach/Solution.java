import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] languageSets =
        Arrays.stream(languages)
            .map(l -> Arrays.stream(l).boxed().collect(Collectors.toSet()))
            .toArray(Set[]::new);
    Boolean[] communicated =
        Arrays.stream(friendships)
            .map(
                friendship ->
                    !Collections.disjoint(
                        languageSets[friendship[0] - 1], languageSets[friendship[1] - 1]))
            .toArray(Boolean[]::new);

    int result = Integer.MAX_VALUE;
    for (int i = 1; i <= n; ++i) {
      Set<Integer> missingUsers = new HashSet<>();
      for (int j = 0; j < friendships.length; ++j) {
        if (!communicated[j]) {
          for (int u : friendships[j]) {
            if (!languageSets[u - 1].contains(i)) {
              missingUsers.add(u);
            }
          }
        }
      }

      result = Math.min(result, missingUsers.size());
    }

    return result;
  }
}
