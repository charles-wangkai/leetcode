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
        return String.format(String.format("%%1$%ds", nums.length), Integer.toBinaryString(i))
            .replace(' ', '0');
      }
    }
  }
}
