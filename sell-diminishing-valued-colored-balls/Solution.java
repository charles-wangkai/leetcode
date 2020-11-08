import java.util.Arrays;

class Solution {
  static final int MODULUS = 1_000_000_007;

  public int maxProfit(int[] inventory, int orders) {
    int result = -1;
    int lower = 1;
    int upper = Arrays.stream(inventory).max().getAsInt();
    while (lower <= upper) {
      int middle = (lower + upper) / 2;
      long sold = Arrays.stream(inventory).mapToLong(x -> Math.max(0, x - middle + 1)).sum();

      if (sold >= orders) {
        result =
            subtractMod(
                Arrays.stream(inventory)
                    .filter(x -> x >= middle)
                    .map(x -> (int) (((x + middle) * (x - middle + 1L) / 2) % MODULUS))
                    .reduce(this::addMod)
                    .getAsInt(),
                multiplyMod(middle, (int) ((sold - orders) % MODULUS)));

        lower = middle + 1;
      } else {
        upper = middle - 1;
      }
    }

    return result;
  }

  int addMod(int x, int y) {
    return (x + y) % MODULUS;
  }

  int subtractMod(int x, int y) {
    return (x - y + MODULUS) % MODULUS;
  }

  int multiplyMod(int x, int y) {
    return (int) ((long) x * y % MODULUS);
  }
}
