import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int longestConsecutive(int[] nums) {
    Set<Integer> numbers = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    int maxLength = 0;
    while (!numbers.isEmpty()) {
      int middle = numbers.iterator().next();
      int length = 0;
      for (int i = middle; numbers.contains(i); ++i) {
        numbers.remove(i);
        ++length;
      }
      for (int i = middle - 1; numbers.contains(i); --i) {
        numbers.remove(i);
        ++length;
      }

      maxLength = Math.max(maxLength, length);
    }

    return maxLength;
  }
}
