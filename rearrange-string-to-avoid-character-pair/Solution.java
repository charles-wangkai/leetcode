class Solution {
  public String rearrangeString(String s, char x, char y) {
    return String.valueOf(y).repeat((int) s.chars().filter(c -> c == y).count())
        + s.replace(String.valueOf(y), "");
  }
}