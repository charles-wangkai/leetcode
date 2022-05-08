import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String largestGoodInteger(String num) {
    return IntStream.rangeClosed(0, 9)
        .mapToObj(i -> String.valueOf(i).repeat(3))
        .filter(num::contains)
        .max(Comparator.naturalOrder())
        .orElse("");
  }
}