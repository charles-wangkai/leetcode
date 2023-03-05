import java.util.Comparator;

class Solution {
  public int splitNum(int num) {
    int[] digits =
        String.valueOf(num)
            .chars()
            .map(c -> c - '0')
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

    int result = 0;
    int placeValue = 1;
    for (int i = 0; i < digits.length; ++i) {
      result += digits[i] * placeValue;

      if (i % 2 == 1) {
        placeValue *= 10;
      }
    }

    return result;
  }
}
