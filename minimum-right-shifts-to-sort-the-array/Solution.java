import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int minimumRightShifts(List<Integer> nums) {
    int beginIndex =
        IntStream.range(0, nums.size()).boxed().min(Comparator.comparing(nums::get)).get();

    return IntStream.range(0, nums.size() - 1)
            .allMatch(
                i ->
                    nums.get((beginIndex + i) % nums.size())
                        < nums.get((beginIndex + i + 1) % nums.size()))
        ? ((nums.size() - beginIndex) % nums.size())
        : -1;
  }
}
