import java.util.stream.IntStream;

class Solution {
  public boolean sumOfNumberAndReverse(int num) {
    return IntStream.rangeClosed(0, num)
        .anyMatch(
            x ->
                x + Integer.parseInt(new StringBuilder(String.valueOf(x)).reverse().toString())
                    == num);
  }
}
