import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String getEncryptedString(String s, int k) {
    return IntStream.range(0, s.length())
        .mapToObj(i -> s.charAt((i + k) % s.length()))
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}