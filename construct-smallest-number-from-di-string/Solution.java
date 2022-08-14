import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  int solution;

  public String smallestNumber(String pattern) {
    solution = Integer.MAX_VALUE;
    int[] digits = IntStream.rangeClosed(1, pattern.length() + 1).toArray();
    search(pattern, digits, 0);

    return String.valueOf(solution);
  }

  void search(String pattern, int[] digits, int index) {
    if (index == digits.length) {
      solution =
          Math.min(
              solution,
              Integer.parseInt(
                  Arrays.stream(digits).mapToObj(String::valueOf).collect(Collectors.joining())));

      return;
    }

    for (int i = index; i < digits.length; ++i) {
      swap(digits, i, index);

      if (index == 0
          || (pattern.charAt(index - 1) == 'I' && digits[index] > digits[index - 1])
          || (pattern.charAt(index - 1) == 'D' && digits[index] < digits[index - 1])) {
        search(pattern, digits, index + 1);
      }

      swap(digits, i, index);
    }
  }

  void swap(int[] a, int index1, int index2) {
    int temp = a[index1];
    a[index1] = a[index2];
    a[index2] = temp;
  }
}