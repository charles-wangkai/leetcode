import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public String findDifferentBinaryString(String[] nums) {
    Set<Integer> seen =
        Arrays.stream(nums)
            .mapToInt(num -> Integer.parseInt(num, 2))
            .boxed()
            .collect(Collectors.toSet());

    for (int i = 0; ; ++i) {
      if (!seen.contains(i)) {
        String result = Integer.toBinaryString(i);
        result = "0".repeat(nums.length - result.length()) + result;

        return result;
      }
    }
  }
}
