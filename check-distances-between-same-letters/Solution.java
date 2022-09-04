import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public boolean checkDistances(String s, int[] distance) {
    Map<Character, List<Integer>> letterToIndices = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      char letter = s.charAt(i);
      letterToIndices.putIfAbsent(letter, new ArrayList<>());
      letterToIndices.get(letter).add(i);
    }

    return letterToIndices.keySet().stream()
        .allMatch(
            letter ->
                letterToIndices.get(letter).get(1) - letterToIndices.get(letter).get(0)
                    == distance[letter - 'a'] + 1);
  }
}