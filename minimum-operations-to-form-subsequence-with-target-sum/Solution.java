import java.util.List;

class Solution {
  static final int LIMIT = 31;

  public int minOperations(List<Integer> nums, int target) {
    int[] counts = new int[LIMIT];
    for (int num : nums) {
      ++counts[Integer.numberOfTrailingZeros(num)];
    }

    int result = 0;
    for (int i = 0; i < counts.length; ++i) {
      if (((target >> i) & 1) == 1) {
        if (counts[i] == 0) {
          int index = i + 1;
          while (index != counts.length && counts[index] == 0) {
            ++index;
          }
          if (index == counts.length) {
            return -1;
          }

          for (int j = index; j > i; --j) {
            --counts[j];
            counts[j - 1] += 2;

            ++result;
          }
        }

        --counts[i];
      }

      if (i != counts.length - 1) {
        counts[i + 1] += counts[i] / 2;
      }
    }

    return result;
  }
}
