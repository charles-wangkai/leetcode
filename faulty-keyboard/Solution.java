class Solution {
  public String finalString(String s) {
    StringBuilder result = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (c == 'i') {
        result.reverse();
      } else {
        result.append(c);
      }
    }

    return result.toString();
  }
}
