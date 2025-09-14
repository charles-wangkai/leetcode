import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[][] generateSchedule(int n) {
    Set<Match> matches =
        IntStream.range(0, n)
            .boxed()
            .flatMap(
                home ->
                    IntStream.range(0, n)
                        .filter(away -> away != home)
                        .mapToObj(away -> new Match(home, away)))
            .collect(Collectors.toSet());

    int[] rests = new int[n];
    Arrays.fill(rests, 2 * (n - 1));

    List<Match> schedule = new ArrayList<>();
    while (!matches.isEmpty()) {
      Optional<Match> chosen =
          matches.stream()
              .filter(
                  match ->
                      schedule.isEmpty()
                          || (match.home() != schedule.getLast().home()
                              && match.home() != schedule.getLast().away()
                              && match.away() != schedule.getLast().home()
                              && match.away() != schedule.getLast().away()))
              .max(Comparator.comparing(match -> rests[match.home()] + rests[match.away()]));
      if (chosen.isEmpty()) {
        return new int[0][];
      }

      matches.remove(chosen.get());
      schedule.add(chosen.get());

      --rests[chosen.get().home()];
      --rests[chosen.get().away()];
    }

    return schedule.stream()
        .map(match -> new int[] {match.home(), match.away()})
        .toArray(int[][]::new);
  }
}

record Match(int home, int away) {}
