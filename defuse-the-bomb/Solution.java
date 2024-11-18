import java.util.stream.IntStream;

class Solution {
  public int[] decrypt(int[] code, int k) {
    return IntStream.range(0, code.length)
        .map(
            i ->
                ((k >= 0) ? IntStream.rangeClosed(1, k) : IntStream.rangeClosed(k, -1))
                    .map(j -> code[Math.floorMod(i + j, code.length)])
                    .sum())
        .toArray();
  }
}
