import java.util.List;

class Solution {
  public String findLongestWord(String s, List<String> d) {
    String result = null;
    for (String word : d) {
      if (canForm(s, word)
          && (result == null
              || word.length() > result.length()
              || (word.length() == result.length() && word.compareTo(result) < 0))) {
        result = word;
      }
    }

    return (result == null) ? "" : result;
  }

  boolean canForm(String s, String word) {
    int fromIndex = 0;
    for (char ch : word.toCharArray()) {
      int index = s.indexOf(ch, fromIndex);

      if (index == -1) {
        return false;
      }

      fromIndex = index + 1;
    }

    return true;
  }
}
