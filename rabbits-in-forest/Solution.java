import java.util.HashMap;
import java.util.Map;

public class Solution {
  public int numRabbits(int[] answers) {
    Map<Integer, Integer> size2count = new HashMap<>();
    for (int answer : answers) {
      int size = answer + 1;
      size2count.put(size, size2count.getOrDefault(size, 0) + 1);
    }

    return size2count.entrySet().stream()
        .mapToInt(
            entry ->
                entry.getKey()
                    * (entry.getValue() / entry.getKey()
                        + (entry.getValue() % entry.getKey() == 0 ? 0 : 1)))
        .sum();
  }
}
