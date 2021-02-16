import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> letterCasePermutation(String S) {
    List<String> created = new ArrayList<>();
    search(created, S, "");

    return created;
  }

  void search(List<String> created, String S, String transformed) {
    if (transformed.length() == S.length()) {
      created.add(transformed);

      return;
    }

    char ch = S.charAt(transformed.length());
    if (Character.isDigit(ch)) {
      search(created, S, transformed + ch);
    } else {
      search(created, S, transformed + Character.toLowerCase(ch));
      search(created, S, transformed + Character.toUpperCase(ch));
    }
  }
}
