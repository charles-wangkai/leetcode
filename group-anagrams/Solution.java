import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> keyToWords = new HashMap<>();
    for (String str : strs) {
      String key = generateKey(str);
      keyToWords.putIfAbsent(key, new ArrayList<>());
      keyToWords.get(key).add(str);
    }

    return List.copyOf(keyToWords.values());
  }

  String generateKey(String word) {
    return word.chars()
        .sorted()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
