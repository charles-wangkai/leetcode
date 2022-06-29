import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
  public int[][] reconstructQueue(int[][] people) {
    Arrays.sort(
        people,
        Comparator.comparing((int[] p) -> p[0])
            .thenComparing(Comparator.comparing((int[] p) -> p[1]).reversed()));

    List<Integer> indices = IntStream.range(0, people.length).boxed().collect(Collectors.toList());

    int[][] queue = new int[people.length][];
    for (int[] person : people) {
      queue[indices.remove(person[1])] = person;
    }

    return queue;
  }
}
