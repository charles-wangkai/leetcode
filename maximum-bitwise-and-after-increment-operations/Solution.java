import java.util.Arrays;

class Solution {
  static final int BIT_NUM = 31;

  public int maximumAND(int[] nums, int k, int m) {
    int result = 0;
    for (int b = BIT_NUM - 1; b >= 0; --b) {
      if (check(nums, k, m, result + (1 << b))) {
        result += 1 << b;
      }
    }

    return result;
  }

  boolean check(int[] nums, int k, int m, int and) {
    return Arrays.stream(nums)
            .map(
                num -> {
                  int target = 0;
                  boolean tight = true;
                  for (int b = BIT_NUM - 1; b >= 0; --b) {
                    if (((and >> b) & 1) == 1) {
                      target += 1 << b;

                      if (((num >> b) & 1) == 0) {
                        tight = false;
                      }
                    } else if (tight && ((num >> b) & 1) == 1) {
                      target += 1 << b;
                    }
                  }

                  return target - num;
                })
            .sorted()
            .limit(m)
            .asLongStream()
            .sum()
        <= k;
  }
}