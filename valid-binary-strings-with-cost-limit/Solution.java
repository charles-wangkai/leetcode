import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> generateValidStrings(int n, int k) {
    return IntStream.range(0, 1 << n)
        .mapToObj(Integer::toBinaryString)
        .map(s -> "0".repeat(n - s.length()) + s)
        .filter(
            s ->
                !s.contains("11")
                    && IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '1').sum() <= k)
        .toList();
  }
}