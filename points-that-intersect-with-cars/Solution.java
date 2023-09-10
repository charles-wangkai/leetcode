import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int numberOfPoints(List<List<Integer>> nums) {
    return (int)
        nums.stream()
            .flatMapToInt(num -> IntStream.rangeClosed(num.get(0), num.get(1)))
            .distinct()
            .count();
  }
}
