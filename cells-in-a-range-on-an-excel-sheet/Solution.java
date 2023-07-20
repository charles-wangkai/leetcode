import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> cellsInRange(String s) {
    char minC = s.charAt(0);
    char minR = s.charAt(1);
    char maxC = s.charAt(3);
    char maxR = s.charAt(4);

    return IntStream.rangeClosed(minC, maxC)
        .boxed()
        .flatMap(c -> IntStream.rangeClosed(minR, maxR).mapToObj(r -> String.format("%c%c", c, r)))
        .toList();
  }
}
