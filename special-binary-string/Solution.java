import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
  public String makeLargestSpecial(String S) {
    List<String> parts = new ArrayList<>();
    int count = 0;
    int startIndex = 0;
    for (int i = 0; i < S.length(); i++) {
      if (S.charAt(i) == '1') {
        count++;
      } else {
        count--;
      }

      if (count == 0) {
        parts.add('1' + makeLargestSpecial(S.substring(startIndex + 1, i)) + '0');
        startIndex = i + 1;
      }
    }
    Collections.sort(parts, Collections.reverseOrder());
    return String.join("", parts);
  }
}
