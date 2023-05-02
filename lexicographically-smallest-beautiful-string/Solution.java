class Solution {
  public String smallestBeautifulString(String s, int k) {
    return search(s, k, new char[s.length()], false, 0);
  }

  String search(String s, int k, char[] letters, boolean free, int index) {
    if (index == letters.length) {
      return new String(letters);
    }

    for (char letter = free ? 'a' : (char) (s.charAt(index) + ((index == s.length() - 1) ? 1 : 0));
        letter <= 'a' + k - 1;
        ++letter) {
      if ((index < 2 || letter != letters[index - 2])
          && (index < 1 || letter != letters[index - 1])) {
        letters[index] = letter;
        String result = search(s, k, letters, free || letter != s.charAt(index), index + 1);
        if (!result.isEmpty()) {
          return result;
        }
      }
    }

    return "";
  }
}
