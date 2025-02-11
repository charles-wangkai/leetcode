class Solution {
  public String removeOccurrences(String s, String part) {
    while (true) {
      int index = s.indexOf(part);
      if (index == -1) {
        break;
      }

      s = s.substring(0, index) + s.substring(index + part.length());
    }

    return s;
  }
}
