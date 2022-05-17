import java.util.stream.IntStream;

class Solution {
  public int divisorSubstrings(int num, int k) {
    String s = String.valueOf(num);

    return (int)
        IntStream.rangeClosed(0, s.length() - k)
            .filter(
                i -> {
                  int value = Integer.parseInt(s.substring(i, i + k));

                  return value != 0 && num % value == 0;
                })
            .count();
  }
}