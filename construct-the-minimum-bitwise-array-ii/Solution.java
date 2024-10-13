import java.util.List;

class Solution {
  public int[] minBitwiseArray(List<Integer> nums) {
    return nums.stream()
        .mapToInt(
            num -> {
              if (num == 2) {
                return -1;
              }

              int b = 0;
              while (((num >> (b + 1)) & 1) == 1) {
                ++b;
              }

              return num - (1 << b);
            })
        .toArray();
  }
}