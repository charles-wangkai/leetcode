import java.util.stream.IntStream;

class Solution {
  public int[] decode(int[] encoded) {
    int[] result = new int[encoded.length + 1];
    result[0] =
        IntStream.rangeClosed(1, result.length).reduce((x, y) -> x ^ y).getAsInt()
            ^ IntStream.range(0, encoded.length)
                .filter(i -> i % 2 != 0)
                .map(i -> encoded[i])
                .reduce((x, y) -> x ^ y)
                .getAsInt();
    for (int i = 1; i < result.length; ++i) {
      result[i] = result[i - 1] ^ encoded[i - 1];
    }

    return result;
  }
}
