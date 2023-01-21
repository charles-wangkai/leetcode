import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    for (int length1 = 1; length1 < s.length(); ++length1) {
      for (int length2 = 1; length1 + length2 < s.length(); ++length2) {
        for (int length3 = 1; length1 + length2 + length3 < s.length(); ++length3) {
          String part1 = s.substring(0, length1);
          String part2 = s.substring(length1, length1 + length2);
          String part3 = s.substring(length1 + length2, length1 + length2 + length3);
          String part4 = s.substring(length1 + length2 + length3);
          if (isValid(part1) && isValid(part2) && isValid(part3) && isValid(part4)) {
            result.add(String.format("%s.%s.%s.%s", part1, part2, part3, part4));
          }
        }
      }
    }

    return result;
  }

  boolean isValid(String part) {
    long value = Long.parseLong(part);

    return value >= 0 && value <= 255 && String.valueOf(value).equals(part);
  }
}
