import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public int nextGreaterElement(int n) {
    String s = String.valueOf(n);

    int index = s.length() - 2;
    while (index >= 0 && s.charAt(index) >= s.charAt(index + 1)) {
      --index;
    }
    if (index == -1) {
      return -1;
    }

    char current = s.charAt(index);
    List<Character> digits = new ArrayList<>();
    for (int i = index; i < s.length(); ++i) {
      digits.add(s.charAt(i));
    }

    Collections.sort(digits);

    String result = s.substring(0, index);
    for (int i = 0; ; ++i) {
      char digit = digits.get(i);
      if (digit > current) {
        result += digit;
        digits.remove(i);

        for (char d : digits) {
          result += d;
        }

        break;
      }
    }

    return (Long.parseLong(result) > Integer.MAX_VALUE) ? -1 : Integer.parseInt(result);
  }
}
