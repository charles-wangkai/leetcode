class Solution {
  public String clearDigits(String s) {
    StringBuilder result = new StringBuilder();
    for (char c : s.toCharArray()) {
      if (Character.isDigit(c)) {
        result.deleteCharAt(result.length() - 1);
      } else {
        result.append(c);
      }
    }

    return result.toString();
  }
}