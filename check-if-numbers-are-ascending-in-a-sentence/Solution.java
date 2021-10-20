import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public boolean areNumbersAscending(String s) {
    int[] values =
        Arrays.stream(s.split(" "))
            .filter(p -> Character.isDigit(p.charAt(0)))
            .mapToInt(Integer::parseInt)
            .toArray();

    return IntStream.range(0, values.length - 1).allMatch(i -> values[i] < values[i + 1]);
  }
}
