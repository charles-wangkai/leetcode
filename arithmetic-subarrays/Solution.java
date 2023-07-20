import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
    return IntStream.range(0, l.length)
        .mapToObj(
            i -> {
              int[] sorted = IntStream.rangeClosed(l[i], r[i]).map(j -> nums[j]).sorted().toArray();

              return IntStream.range(0, sorted.length - 1)
                  .allMatch(j -> sorted[j + 1] - sorted[j] == sorted[1] - sorted[0]);
            })
        .toList();
  }
}
