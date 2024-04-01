class Solution {
  public int lengthOfLastWord(String s) {
    return s.stripTrailing().length() - 1 - s.stripTrailing().lastIndexOf(' ');
  }
}
