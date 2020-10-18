import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public double trimMean(int[] arr) {
    Arrays.sort(arr);

    return IntStream.range(arr.length / 20, arr.length - arr.length / 20)
        .map(i -> arr[i])
        .average()
        .getAsDouble();
  }
}
