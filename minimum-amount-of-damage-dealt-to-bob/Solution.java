import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public long minDamage(int power, int[] damage, int[] health) {
    int[] times = Arrays.stream(health).map(h -> (h + power - 1) / power).toArray();
    int[] sortedIndices =
        IntStream.range(0, damage.length)
            .boxed()
            .sorted((i1, i2) -> Integer.compare(damage[i2] * times[i1], damage[i1] * times[i2]))
            .mapToInt(Integer::intValue)
            .toArray();

    long result = 0;
    int rest = Arrays.stream(damage).sum();
    for (int index : sortedIndices) {
      result += (long) rest * times[index];
      rest -= damage[index];
    }

    return result;
  }
}