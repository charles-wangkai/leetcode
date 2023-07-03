import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public boolean buddyStrings(String s, String goal) {
    Map<Character, Integer> letterToCountS = buildLetterToCount(s);
    Map<Character, Integer> letterToCountGoal = buildLetterToCount(goal);
    if (!letterToCountS.equals(letterToCountGoal)) {
      return false;
    }

    if (s.equals(goal)) {
      return letterToCountS.values().stream().anyMatch(count -> count >= 2);
    }

    int[] diffIndices =
        IntStream.range(0, s.length()).filter(i -> s.charAt(i) != goal.charAt(i)).toArray();

    return diffIndices.length == 2 && s.charAt(diffIndices[0]) == goal.charAt(diffIndices[1]);
  }

  Map<Character, Integer> buildLetterToCount(String str) {
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (char letter : str.toCharArray()) {
      letterToCount.put(letter, letterToCount.getOrDefault(letter, 0) + 1);
    }

    return letterToCount;
  }
}
