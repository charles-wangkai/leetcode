import java.util.stream.IntStream;

class Solution {
  public long minimumSteps(String s) {
    int[] zeroIndices = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '0').toArray();

    return IntStream.range(0, zeroIndices.length).map(i -> zeroIndices[i] - i).asLongStream().sum();
  }
}
