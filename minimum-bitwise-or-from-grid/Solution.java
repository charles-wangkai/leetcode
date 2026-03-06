import java.util.Arrays;

class Solution {
  static final int BIT_NUM = 17;

  public int minimumOR(int[][] grid) {
    int result = 0;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      int b_ = b;
      int result_ = result;
      if (Arrays.stream(grid)
          .anyMatch(
              line ->
                  Arrays.stream(line)
                      .filter(
                          x -> ((x >> (b_ + 1)) | (result_ >> (b_ + 1))) == (result_ >> (b_ + 1)))
                      .allMatch(x -> ((x >> b_) & 1) == 1))) {
        result += 1 << b;
      }
    }

    return result;
  }
}