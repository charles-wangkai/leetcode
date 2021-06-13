import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean mergeTriplets(int[][] triplets, int[] target) {
    int[][] candidates =
        Arrays.stream(triplets)
            .filter(
                triplet ->
                    triplet[0] <= target[0] && triplet[1] <= target[1] && triplet[2] <= target[2])
            .toArray(int[][]::new);

    return candidates.length != 0
        && IntStream.range(0, target.length)
            .allMatch(
                i ->
                    Arrays.stream(candidates)
                            .mapToInt(candidate -> candidate[i])
                            .reduce(Math::max)
                            .getAsInt()
                        == target[i]);
  }
}
