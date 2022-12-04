import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long dividePlayers(int[] skill) {
    Arrays.sort(skill);

    return (IntStream.range(0, skill.length / 2)
                .map(i -> skill[i] + skill[skill.length - 1 - i])
                .distinct()
                .count()
            == 1)
        ? IntStream.range(0, skill.length / 2)
            .map(i -> skill[i] * skill[skill.length - 1 - i])
            .asLongStream()
            .sum()
        : -1;
  }
}
