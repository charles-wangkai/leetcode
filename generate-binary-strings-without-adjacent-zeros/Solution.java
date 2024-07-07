import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> validStrings(int n) {
    return IntStream.range(0, 1 << n)
        .mapToObj(mask -> ("%" + n + "s").formatted(Integer.toBinaryString(mask)).replace(' ', '0'))
        .filter(s -> !s.contains("00"))
        .toList();
  }
}