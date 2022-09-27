import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String[] sortPeople(String[] names, int[] heights) {
    return IntStream.range(0, names.length)
        .boxed()
        .sorted(Comparator.comparing((Integer i) -> heights[i]).reversed())
        .map(i -> names[i])
        .toArray(String[]::new);
  }
}