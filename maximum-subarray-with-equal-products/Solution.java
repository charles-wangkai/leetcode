import java.util.Arrays;

class Solution {
  public int maxLength(int[] nums) {
    int maxProduct =
        Arrays.stream(nums).reduce(this::lcm).getAsInt() * Arrays.stream(nums).max().getAsInt();

    int result = 0;
    for (int i = 0; i < nums.length; ++i) {
      int product = nums[i];
      int l = nums[i];
      int g = nums[i];
      if (product == l * g) {
        result = Math.max(result, 1);
      }
      for (int j = i + 1; j < nums.length; ++j) {
        product *= nums[j];
        if (product > maxProduct) {
          break;
        }

        l = lcm(l, nums[j]);
        g = gcd(g, nums[j]);

        if (product == l * g) {
          result = Math.max(result, j - i + 1);
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