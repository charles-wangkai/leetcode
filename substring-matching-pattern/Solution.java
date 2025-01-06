class Solution {
  public boolean hasMatch(String s, String p) {
    String[] parts = p.split("\\*", -1);
    int index = s.indexOf(parts[0]);

    return index != -1 && s.indexOf(parts[1], index + parts[0].length()) != -1;
  }
}