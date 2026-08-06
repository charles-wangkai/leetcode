import java.util.stream.IntStream;

class Solution {
  public int smallestNumber(int n, int t) {
    return IntStream.iterate(n, x -> x + 1)
        .filter(
            x ->
                String.valueOf(x).chars().map(c -> c - '0').reduce(1, (acc, e) -> acc * e) % t == 0)
        .findFirst()
        .getAsInt();
  }
}