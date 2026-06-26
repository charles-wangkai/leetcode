import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int maxTotalValue(int[] value, int[] decay, int m) {
    int threshold = -1;
    int lower = 0;
    int upper = Arrays.stream(value).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      if (computeSelectionNum(value, decay, middle) <= m) {
        threshold = middle;
        upper = middle - 1;
      } else {
        lower = middle + 1;
      }
    }

    long sum = 0;
    List<Integer> candidates = new ArrayList<>();
    for (int i = 0; i < value.length; ++i) {
      if (threshold == -1) {
        candidates.add(value[i]);
      } else if (value[i] >= threshold) {
        int count = (value[i] - threshold) / decay[i] + 1;
        sum += (long) count * value[i] - count * (count - 1L) / 2 * decay[i];

        m -= count;
        candidates.add(value[i] - count * decay[i]);
      }
    }
    sum +=
        candidates.stream()
            .filter(candidate -> candidate >= 0)
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .asLongStream()
            .limit(m)
            .sum();

    return (int) (sum % 1_000_000_007);
  }

  long computeSelectionNum(int[] value, int[] decay, int threshold) {
    return IntStream.range(0, value.length)
        .map(
            i -> {
              if (value[i] < threshold) {
                return 0;
              }

              return (value[i] - threshold) / decay[i] + 1;
            })
        .asLongStream()
        .sum();
  }
}