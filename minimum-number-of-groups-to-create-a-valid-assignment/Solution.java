import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public int minGroupsForValidAssignment(int[] balls) {
    Map<Integer, Integer> ballToCount = new HashMap<>();
    for (int ball : balls) {
      ballToCount.put(ball, ballToCount.getOrDefault(ball, 0) + 1);
    }

    int[] counts = ballToCount.values().stream().sorted().mapToInt(Integer::intValue).toArray();
    for (int size = counts[0]; ; --size) {
      int size_ = size;
      if (Arrays.stream(counts).allMatch(count -> count % size_ <= count / size_)) {
        return Arrays.stream(counts).map(count -> (count + size_) / (size_ + 1)).sum();
      }
    }
  }
}
