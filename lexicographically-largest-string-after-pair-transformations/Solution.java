import java.util.Arrays;

class Solution {
  public String[] largestString(int[] nums) {
    return Arrays.stream(nums)
        .mapToObj(
            x -> {
              StringBuilder result = new StringBuilder();
              while (x != 0) {
                for (int i = 25; ; --i) {
                  if (x >= 1 << i) {
                    result.append((char) ('a' + i));
                    x -= 1 << i;

                    break;
                  }
                }
              }

              return result.toString();
            })
        .toArray(String[]::new);
  }
}