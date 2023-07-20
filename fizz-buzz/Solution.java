import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<String> fizzBuzz(int n) {
    return IntStream.rangeClosed(1, n)
        .mapToObj(
            x -> {
              if (x % 15 == 0) {
                return "FizzBuzz";
              } else if (x % 3 == 0) {
                return "Fizz";
              } else if (x % 5 == 0) {
                return "Buzz";
              } else {
                return String.valueOf(x);
              }
            })
        .toList();
  }
}
