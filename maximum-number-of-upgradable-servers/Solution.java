import java.util.stream.IntStream;

class Solution {
  public int[] maxUpgrades(int[] count, int[] upgrade, int[] sell, int[] money) {
    return IntStream.range(0, count.length)
        .map(
            i -> {
              int result = 0;
              int lower = 1;
              int upper = count[i];
              while (lower <= upper) {
                int middle = (lower + upper) / 2;
                if ((long) middle * upgrade[i] <= money[i] + (long) (count[i] - middle) * sell[i]) {
                  result = middle;
                  lower = middle + 1;
                } else {
                  upper = middle - 1;
                }
              }

              return result;
            })
        .toArray();
  }
}