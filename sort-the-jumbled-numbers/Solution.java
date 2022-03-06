import java.util.Comparator;
import java.util.stream.IntStream;

class Solution {
  public int[] sortJumbled(int[] mapping, int[] nums) {
    return IntStream.range(0, nums.length)
        .boxed()
        .sorted(Comparator.comparing((Integer i) -> map(mapping, nums[i])).thenComparing(i -> i))
        .mapToInt(i -> nums[i])
        .toArray();
  }

  int map(int[] mapping, int x) {
    int result = 0;
    String s = String.valueOf(x);
    for (char c : s.toCharArray()) {
      result = result * 10 + mapping[c - '0'];
    }

    return result;
  }
}