import java.util.stream.IntStream;

class Solution {
  public int[] decrypt(int[] code, int k) {
    return IntStream.range(0, code.length)
        .map(
            i -> {
              if (k < 0) {
                return IntStream.rangeClosed(k, -1)
                    .map(j -> code[(i + j + code.length) % code.length])
                    .sum();
              } else if (k > 0) {
                return IntStream.rangeClosed(1, k).map(j -> code[(i + j) % code.length]).sum();
              } else {
                return 0;
              }
            })
        .toArray();
  }
}
