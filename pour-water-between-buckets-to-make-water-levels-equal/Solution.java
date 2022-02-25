import java.util.Arrays;

class Solution {
  static final int ITERATION_NUM = 50;

  public double equalizeWater(int[] buckets, int loss) {
    double lower = Arrays.stream(buckets).min().getAsInt();
    double upper = Arrays.stream(buckets).max().getAsInt();
    for (int i = 0; i < ITERATION_NUM; ++i) {
      double middle = (lower + upper) / 2;
      if (Arrays.stream(buckets).mapToDouble(b -> Math.max(0, b - middle)).sum()
              * (1 - loss / 100.0)
          > Arrays.stream(buckets).mapToDouble(b -> Math.max(0, middle - b)).sum()) {
        lower = middle;
      } else {
        upper = middle;
      }
    }

    return lower;
  }
}