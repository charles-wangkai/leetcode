import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
    @SuppressWarnings("unchecked")
    Set<Integer>[] languageSets =
        Arrays.stream(languages)
            .map(l -> Arrays.stream(l).boxed().collect(Collectors.toSet()))
            .toArray(Set[]::new);

    int[] neededIndices =
        IntStream.range(0, friendships.length)
            .filter(
                i ->
                    Collections.disjoint(
                        languageSets[friendships[i][0] - 1], languageSets[friendships[i][1] - 1]))
            .toArray();

    return IntStream.rangeClosed(1, n)
        .map(
            language ->
                (int)
                    Arrays.stream(neededIndices)
                        .flatMap(
                            index -> IntStream.of(friendships[index][0], friendships[index][1]))
                        .filter(u -> !languageSets[u - 1].contains(language))
                        .distinct()
                        .count())
        .min()
        .getAsInt();
  }
}
