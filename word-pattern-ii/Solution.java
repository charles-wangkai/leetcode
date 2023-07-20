import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Solution {
  public boolean wordPatternMatch(String pattern, String str) {
    if (pattern.length() > str.length()) {
      return false;
    }
    if (pattern.isEmpty()) {
      return str.isEmpty();
    }

    Map<Character, Integer> letter2count = new HashMap<>();
    for (char letter : pattern.toCharArray()) {
      if (!letter2count.containsKey(letter)) {
        letter2count.put(letter, 0);
      }
      letter2count.put(letter, letter2count.get(letter) + 1);
    }

    return isMatched(
        pattern,
        str,
        new ArrayList<>(letter2count.keySet()),
        letter2count,
        new HashMap<>(),
        0,
        pattern.length(),
        str.length());
  }

  boolean isMatched(
      String pattern,
      String str,
      List<Character> letters,
      Map<Character, Integer> letter2count,
      Map<Character, Integer> letter2length,
      int index,
      int remainPatternLength,
      int remainStrLength) {
    if (index == letters.size()) {
      String[] words = split(pattern, str, letter2length);
      return isWordsMatched(pattern, words);
    }

    char letter = letters.get(index);
    int count = letter2count.get(letter);

    int beginLength;
    if (index == letters.size() - 1) {
      if (remainStrLength % remainPatternLength != 0) {
        return false;
      }

      beginLength = remainStrLength / remainPatternLength;
    } else {
      beginLength = 1;
    }

    for (int length = beginLength;
        remainPatternLength - count <= remainStrLength - length * count;
        length++) {
      letter2length.put(letter, length);

      if (isMatched(
          pattern,
          str,
          letters,
          letter2count,
          letter2length,
          index + 1,
          remainPatternLength - count,
          remainStrLength - length * count)) {
        return true;
      }

      letter2length.remove(letter);
    }
    return false;
  }

  String[] split(String pattern, String str, Map<Character, Integer> letter2length) {
    String[] words = new String[pattern.length()];
    int beginIndex = 0;
    for (int i = 0; i < words.length; i++) {
      int endIndex = beginIndex + letter2length.get(pattern.charAt(i));
      words[i] = str.substring(beginIndex, endIndex);
      beginIndex = endIndex;
    }
    return words;
  }

  boolean isWordsMatched(String pattern, String[] words) {
    Map<Character, String> letter2word = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      char letter = pattern.charAt(i);
      String word = words[i];
      if (letter2word.containsKey(letter)) {
        if (!letter2word.get(letter).equals(word)) {
          return false;
        }
      } else {
        letter2word.put(letter, word);
      }
    }

    return letter2word.size() == new HashSet<>(letter2word.values()).size();
  }
}
