import java.util.stream.IntStream;

class Solution {
  public int binaryGap(int n) {
    String s = Integer.toBinaryString(n);
    int[] oneIndices = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '1').toArray();

    return IntStream.range(0, oneIndices.length - 1)
        .map(i -> oneIndices[i + 1] - oneIndices[i])
        .max()
        .orElse(0);
  }
}
