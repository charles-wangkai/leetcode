import java.util.HashMap;
import java.util.Map;

class Solution {
  public boolean isIsomorphic(String s, String t) {
    return generateKey(s).equals(generateKey(t));
  }

  String generateKey(String str) {
    Map<Character, Character> translation = new HashMap<>();
    StringBuilder result = new StringBuilder();
    for (char from : str.toCharArray()) {
      if (!translation.containsKey(from)) {
        translation.put(from, (char) translation.size());
      }

      result.append(translation.get(from));
    }

    return result.toString();
  }
}
