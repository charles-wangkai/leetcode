import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public int longestBalanced(String s) {
    return IntStream.of(
            computeMaxLengthForOneLetter(s, 'a'),
            computeMaxLengthForOneLetter(s, 'b'),
            computeMaxLengthForOneLetter(s, 'c'),
            computeMaxLengthForTwoLetter(s, 'a', 'b'),
            computeMaxLengthForTwoLetter(s, 'b', 'c'),
            computeMaxLengthForTwoLetter(s, 'c', 'a'),
            computeMaxLengthForThreeLetter(s))
        .max()
        .getAsInt();
  }

  int computeMaxLengthForOneLetter(String s, char letter) {
    int result = 0;
    int length = 0;
    for (char c : s.toCharArray()) {
      if (c == letter) {
        ++length;
      } else {
        length = 0;
      }

      result = Math.max(result, length);
    }

    return result;
  }

  int computeMaxLengthForTwoLetter(String s, char letter1, char letter2) {
    int result = 0;
    Map<Integer, Integer> diffToLength = new HashMap<>();
    diffToLength.put(0, 0);
    int diff = 0;
    for (int i = 0; i < s.length(); ++i) {
      if (s.charAt(i) == letter1 || s.charAt(i) == letter2) {
        diff += (s.charAt(i) == letter1) ? 1 : -1;

        if (diffToLength.containsKey(diff)) {
          result = Math.max(result, i + 1 - diffToLength.get(diff));
        } else {
          diffToLength.put(diff, i + 1);
        }
      } else {
        diffToLength = new HashMap<>();
        diffToLength.put(0, i + 1);
        diff = 0;
      }
    }

    return result;
  }

  int computeMaxLengthForThreeLetter(String s) {
    int result = 0;
    Map<State, Integer> stateToLength = new HashMap<>();
    stateToLength.put(new State(0, 0), 0);
    Map<Character, Integer> letterToCount = new HashMap<>();
    for (int i = 0; i < s.length(); ++i) {
      letterToCount.put(s.charAt(i), letterToCount.getOrDefault(s.charAt(i), 0) + 1);

      State state =
          new State(
              letterToCount.getOrDefault('b', 0) - letterToCount.getOrDefault('a', 0),
              letterToCount.getOrDefault('c', 0) - letterToCount.getOrDefault('a', 0));
      if (stateToLength.containsKey(state)) {
        result = Math.max(result, i + 1 - stateToLength.get(state));
      } else {
        stateToLength.put(state, i + 1);
      }
    }

    return result;
  }
}

record State(int diffBA, int diffCA) {}
