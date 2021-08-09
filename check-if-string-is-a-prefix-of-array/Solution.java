class Solution {
  public boolean isPrefixString(String s, String[] words) {
    StringBuilder combined = new StringBuilder();
    for (String word : words) {
      combined.append(word);
      if (combined.length() >= s.length()) {
        break;
      }
    }

    return combined.toString().equals(s);
  }
}
