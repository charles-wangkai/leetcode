import java.util.stream.IntStream;

class Solution {
  public int countDistinct(String s) {
    return (int)
        IntStream.range(0, s.length())
            .boxed()
            .flatMap(i -> IntStream.rangeClosed(i + 1, s.length()).mapToObj(j -> s.substring(i, j)))
            .distinct()
            .count();
  }
}
