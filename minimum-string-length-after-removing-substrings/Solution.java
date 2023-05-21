class Solution {
  public int minLength(String s) {
    while (s.contains("AB") || s.contains("CD")) {
      s = s.replace(s.contains("AB") ? "AB" : "CD", "");
    }

    return s.length();
  }
}
