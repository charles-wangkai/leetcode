import java.util.Arrays;

class Solution {
  public int maxLength(int[] nums) {
    int maxProduct =
        Arrays.stream(nums).reduce(this::lcm).getAsInt() * Arrays.stream(nums).max().getAsInt();

    int result = 0;
    for (int beginIndex = 0; beginIndex < nums.length; ++beginIndex) {
      int product = nums[beginIndex];
      int l = nums[beginIndex];
      int g = nums[beginIndex];
      if (product == l * g) {
        result = Math.max(result, 1);
      }

      for (int endIndex = beginIndex + 1; endIndex < nums.length; ++endIndex) {
        product *= nums[endIndex];
        if (product > maxProduct) {
          break;
        }

        l = lcm(l, nums[endIndex]);
        g = gcd(g, nums[endIndex]);

        if (product == l * g) {
          result = Math.max(result, endIndex - beginIndex + 1);
        }
      }
    }

    return result;
  }

  int gcd(int x, int y) {
    return (y == 0) ? x : gcd(y, x % y);
  }

  int lcm(int x, int y) {
    return x / gcd(x, y) * y;
  }
}