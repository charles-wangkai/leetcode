import java.util.stream.IntStream;

class Solution {
  public boolean hasAllCodes(String s, int k) {
    return IntStream.rangeClosed(0, s.length() - k)
            .mapToObj(i -> s.substring(i, i + k))
            .distinct()
            .count()
        == 1 << k;
  }
}
