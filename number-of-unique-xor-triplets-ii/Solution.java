import java.util.stream.IntStream;

class Solution {
  static final int LIMIT = 2048;

  public int uniqueXorTriplets(int[] nums) {
    boolean[] xors = new boolean[LIMIT];
    xors[0] = true;

    for (int i = 0; i < 3; ++i) {
      xors = buildNextXors(xors, nums);
    }

    boolean[] xors_ = xors;
    return (int) IntStream.range(0, xors.length).filter(i -> xors_[i]).count();
  }

  boolean[] buildNextXors(boolean[] xors, int[] nums) {
    boolean[] result = new boolean[xors.length];
    for (int i = 0; i < xors.length; ++i) {
      if (xors[i]) {
        for (int x : nums) {
          result[i ^ x] = true;
        }
      }
    }

    return result;
  }
}