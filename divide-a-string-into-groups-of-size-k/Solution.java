import java.util.stream.IntStream;

class Solution {
  public String[] divideString(String s, int k, char fill) {
    String filled = s + String.valueOf(fill).repeat((k - s.length() % k) % k);

    return IntStream.range(0, filled.length() / k)
        .mapToObj(i -> filled.substring(i * k, i * k + k))
        .toArray(String[]::new);
  }
}