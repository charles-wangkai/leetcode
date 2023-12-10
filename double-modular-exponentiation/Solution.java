import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> getGoodIndices(int[][] variables, int target) {
    return IntStream.range(0, variables.length)
        .filter(
            i ->
                powMod(
                        powMod(variables[i][0], variables[i][1], 10),
                        variables[i][2],
                        variables[i][3])
                    == target)
        .boxed()
        .toList();
  }

  int powMod(int base, int exponent, int m) {
    return IntStream.range(0, exponent).reduce(1, (acc, x) -> multiplyMod(acc, base, m));
  }

  int multiplyMod(int x, int y, int m) {
    return Math.floorMod(x * y, m);
  }
}