import java.util.Arrays;

class Solution {
  public int maximumPopulation(int[][] logs) {
    int maxPopulation =
        Arrays.stream(logs)
            .mapToInt(
                log ->
                    (int) Arrays.stream(logs).filter(l -> log[0] >= l[0] && log[0] < l[1]).count())
            .max()
            .getAsInt();

    return Arrays.stream(logs)
        .filter(
            log ->
                Arrays.stream(logs).filter(l -> log[0] >= l[0] && log[0] < l[1]).count()
                    == maxPopulation)
        .mapToInt(log -> log[0])
        .min()
        .getAsInt();
  }
}
