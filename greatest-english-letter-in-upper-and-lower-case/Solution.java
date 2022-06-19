import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public String greatestLetter(String s) {
    return IntStream.range(0, 26)
        .filter(i -> s.indexOf(i + 'a') != -1 && s.indexOf(i + 'A') != -1)
        .mapToObj(i -> String.valueOf((char) (i + 'A')))
        .max(Comparator.naturalOrder())
        .orElse("");
  }
}