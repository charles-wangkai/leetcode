class Solution {
  public int minInsertions(String s) {
    int result = 0;
    int leftCount = 0;
    int index = 0;
    while (index < s.length()) {
      char current = s.charAt(index);

      if (current == '(') {
        ++leftCount;
      } else {
        if (leftCount == 0) {
          ++result;
        } else {
          --leftCount;
        }

        if (index + 1 != s.length() && s.charAt(index + 1) == ')') {
          ++index;
        } else {
          ++result;
        }
      }

      ++index;
    }
    result += leftCount * 2;

    return result;
  }
}
