import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
    int[] values = buildValues(req_skills, people);

    int[][] minSizes = new int[values.length + 1][1 << req_skills.length];
    Arrays.fill(minSizes[0], Integer.MAX_VALUE);
    minSizes[0][0] = 0;

    for (int i = 0; i < minSizes.length - 1; ++i) {
      minSizes[i + 1] = minSizes[i].clone();

      for (int j = 0; j < minSizes[i].length; ++j) {
        if (minSizes[i][j] != Integer.MAX_VALUE) {
          minSizes[i + 1][j | values[i]] =
              Math.min(minSizes[i + 1][j | values[i]], minSizes[i][j] + 1);
        }
      }
    }

    List<Integer> result = new ArrayList<>();
    int mask = (1 << req_skills.length) - 1;
    for (int i = values.length; i >= 1; --i) {
      if (minSizes[i - 1][mask] != minSizes[i][mask]) {
        for (int prevMask = 0; ; ++prevMask) {
          if ((prevMask | values[i - 1]) == mask
              && minSizes[i - 1][prevMask] == minSizes[i][mask] - 1) {
            result.add(i - 1);
            mask = prevMask;

            break;
          }
        }
      }
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  int[] buildValues(String[] req_skills, List<List<String>> people) {
    Map<String, Integer> skillToIndex =
        IntStream.range(0, req_skills.length)
            .boxed()
            .collect(Collectors.toMap(i -> req_skills[i], i -> i));

    return people.stream()
        .mapToInt(p -> p.stream().map(skillToIndex::get).mapToInt(i -> 1 << i).sum())
        .toArray();
  }
}
