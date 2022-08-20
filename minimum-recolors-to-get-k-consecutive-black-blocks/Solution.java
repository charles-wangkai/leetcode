import java.util.stream.IntStream;

class Solution {
  public int minimumRecolors(String blocks, int k) {
    return IntStream.rangeClosed(0, blocks.length() - k)
        .map(i -> (int) IntStream.range(i, i + k).filter(j -> blocks.charAt(j) == 'W').count())
        .min()
        .getAsInt();
  }
}