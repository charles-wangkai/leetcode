import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public int numberOfBeams(String[] bank) {
    int[] counts =
        Arrays.stream(bank)
            .mapToInt(r -> (int) r.chars().filter(ch -> ch == '1').count())
            .filter(c -> c != 0)
            .toArray();

    return IntStream.range(0, counts.length - 1).map(i -> counts[i] * counts[i + 1]).sum();
  }
}