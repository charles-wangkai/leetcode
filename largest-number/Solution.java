import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
  public String largestNumber(int[] num) {
    if (Arrays.stream(num).allMatch(x -> x == 0)) {
      return "0";
    }

    return Arrays.stream(num)
        .boxed()
        .sorted((x, y) -> String.format("%d%d", y, x).compareTo(String.format("%d%d", x, y)))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
