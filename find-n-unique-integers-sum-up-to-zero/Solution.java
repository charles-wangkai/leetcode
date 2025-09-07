import java.util.stream.IntStream;

class Solution {
  public int[] sumZero(int n) {
    int[] result = new int[n];
    for (int i = 0; i < result.length - 1; ++i) {
      result[i] = i + 1;
    }
    result[result.length - 1] = -IntStream.range(0, result.length - 1).map(i -> result[i]).sum();

    return result;
  }
}
