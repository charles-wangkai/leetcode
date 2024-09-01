import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String stringHash(String s, int k) {
    return IntStream.range(0, s.length() / k)
        .map(i -> s.substring(i * k, i * k + k).chars().map(c -> c - 'a').sum() % 26)
        .mapToObj(x -> (char) (x + 'a'))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}