import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String removeDuplicateLetters(String s) {
    List<LetterState> letterStates =
        IntStream.rangeClosed('a', 'z')
            .mapToObj(c -> new LetterState((char) c, new ArrayDeque<>()))
            .collect(Collectors.toList());

    for (int i = 0; i < s.length(); ++i) {
      letterStates.get(s.charAt(i) - 'a').indices().offerLast(i);
    }

    letterStates.removeIf(letterState -> letterState.indices().isEmpty());

    StringBuilder result = new StringBuilder();
    while (!letterStates.isEmpty()) {
      int minLastIndex =
          letterStates.stream()
              .mapToInt(letterState -> letterState.indices().peekLast())
              .min()
              .getAsInt();

      char selectedLetter = 0;
      int selectedIndex = -1;
      for (LetterState letterState : letterStates) {
        if (letterState.indices().peekFirst() <= minLastIndex) {
          selectedLetter = letterState.letter();
          selectedIndex = letterState.indices().peekFirst();

          break;
        }
      }

      char selectedLetter_ = selectedLetter;
      letterStates.removeIf(letterState -> letterState.letter() == selectedLetter_);

      for (LetterState letterState : letterStates) {
        while (letterState.indices().peekFirst() <= selectedIndex) {
          letterState.indices().pollFirst();
        }
      }

      result.append(selectedLetter);
    }

    return result.toString();
  }
}

record LetterState(char letter, Deque<Integer> indices) {}
