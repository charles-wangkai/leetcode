import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
  public List<List<String>> partition(String s) {
    @SuppressWarnings("unchecked")
    List<List<String>>[] partitions = new List[s.length() + 1];

    for (int i = 0; i < partitions.length; ++i) {
      partitions[i] = new ArrayList<>();
      if (i == 0) {
        partitions[i].add(Collections.emptyList());
      } else {
        for (int j = 1; j <= i; ++j) {
          String last = s.substring(i - j, i);
          if (isPalindrome(last)) {
            for (List<String> p : partitions[i - j]) {
              List<String> part = new ArrayList<>(p);
              part.add(last);

              partitions[i].add(part);
            }
          }
        }
      }
    }

    return partitions[s.length()];
  }

  boolean isPalindrome(String s) {
    return new StringBuilder(s).reverse().toString().equals(s);
  }
}
