import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public int maxRepeating(String sequence, String word) {
    int result = 0;
    while (sequence.contains(
        IntStream.range(0, result + 1).mapToObj(i -> word).collect(Collectors.joining()))) {
      ++result;
    }

    return result;
  }
}
