import java.util.stream.IntStream;

class Solution {
  public int countMonobit(int n) {
    return (int)
        IntStream.rangeClosed(0, n)
            .filter(x -> Integer.toBinaryString(x).chars().distinct().count() == 1)
            .count();
  }
}