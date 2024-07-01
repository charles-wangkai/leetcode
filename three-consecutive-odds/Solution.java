import java.util.stream.IntStream;

class Solution {
  public boolean threeConsecutiveOdds(int[] arr) {
    return IntStream.range(0, arr.length - 2)
        .anyMatch(i -> arr[i] % 2 == 1 && arr[i + 1] % 2 == 1 && arr[i + 2] % 2 == 1);
  }
}