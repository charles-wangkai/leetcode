import java.util.stream.IntStream;

class Solution {
  public int numberCount(int a, int b) {
    return (int)
        IntStream.rangeClosed(a, b)
            .filter(
                x -> {
                  String s = String.valueOf(x);

                  return s.chars().distinct().count() == s.length();
                })
            .count();
  }
}