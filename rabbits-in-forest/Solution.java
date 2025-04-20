import java.util.HashMap;
import java.util.Map;

class Solution {
  public int numRabbits(int[] answers) {
    Map<Integer, Integer> sizeToCount = new HashMap<>();
    for (int answer : answers) {
      int size = answer + 1;
      sizeToCount.put(size, sizeToCount.getOrDefault(size, 0) + 1);
    }

    return sizeToCount.keySet().stream()
        .mapToInt(size -> (sizeToCount.get(size) + size - 1) / size * size)
        .sum();
  }
}
