class Solution {
  public int appendCharacters(String s, String t) {
    int fromIndex = 0;
    for (int i = 0; i < t.length(); ++i) {
      int index = s.indexOf(t.charAt(i), fromIndex);
      if (index == -1) {
        return t.length() - i;
      }

      fromIndex = index + 1;
    }

    return 0;
  }
}
