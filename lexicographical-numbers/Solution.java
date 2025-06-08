import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> lexicalOrder(int n) {
    return IntStream.rangeClosed(1, n)
        .boxed()
        .sorted(Comparator.comparing(String::valueOf))
        .toList();
  }
}
