import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Solution {
  static final Map<Integer, Integer> DIGIT_TO_ROTATED = new HashMap<>();

  static {
    DIGIT_TO_ROTATED.put(0, 0);
    DIGIT_TO_ROTATED.put(1, 1);
    DIGIT_TO_ROTATED.put(8, 8);
    DIGIT_TO_ROTATED.put(2, 5);
    DIGIT_TO_ROTATED.put(5, 2);
    DIGIT_TO_ROTATED.put(6, 9);
    DIGIT_TO_ROTATED.put(9, 6);
  }

  public int rotatedDigits(int N) {
    return (int) IntStream.rangeClosed(1, N).filter(this::isGood).count();
  }

  boolean isGood(int n) {
    String s = String.valueOf(n);
    StringBuilder rotated = new StringBuilder();
    for (char ch : s.toCharArray()) {
      int digit = ch - '0';
      if (!DIGIT_TO_ROTATED.containsKey(digit)) {
        return false;
      }
      rotated.append(DIGIT_TO_ROTATED.get(digit));
    }
    return Integer.parseInt(rotated.toString()) != n;
  }
}
