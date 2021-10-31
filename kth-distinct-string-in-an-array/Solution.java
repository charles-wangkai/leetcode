import java.util.Arrays;
import java.util.stream.IntStream;

class Solution {
  public String kthDistinct(String[] arr, int k) {
    return IntStream.range(0, arr.length)
        .filter(i -> Arrays.stream(arr).filter(s -> s.equals(arr[i])).count() == 1)
        .skip(k - 1)
        .mapToObj(i -> arr[i])
        .findFirst()
        .orElse("");
  }
}
