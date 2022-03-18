import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public String removeDuplicateLetters(String s) {
    List<LetterState> letterStates =
        IntStream.range(0, 26)
            .mapToObj(i -> new LetterState((char) (i + 'a')))
            .collect(Collectors.toList());

    for (int i = 0; i < s.length(); ++i) {
      letterStates.get(s.charAt(i) - 'a').appendIndex(i);
    }

    letterStates.removeIf(letterState -> letterState.indices.isEmpty());

    StringBuilder result = new StringBuilder();
    while (!letterStates.isEmpty()) {
      int minLastIndex =
          letterStates.stream().mapToInt(letterState -> letterState.lastIndex).min().getAsInt();

      char selectedLetter = 0;
      int selectedIndex = -1;
      for (LetterState letterState : letterStates) {
        if (letterState.indices.peek() <= minLastIndex) {
          selectedLetter = letterState.letter;
          selectedIndex = letterState.indices.peek();

          break;
        }
      }

      char selectedLetter_ = selectedLetter;
      letterStates.removeIf(letterState -> letterState.letter == selectedLetter_);

      int selectedIndex_ = selectedIndex;
      letterStates.forEach(
          letterState -> {
            while (letterState.indices.peek() <= selectedIndex_) {
              letterState.indices.poll();
            }
          });

      result.append(selectedLetter);
    }

    return result.toString();
  }
}

class LetterState {
  char letter;
  Queue<Integer> indices = new ArrayDeque<>();
  int lastIndex = -1;

  LetterState(char letter) {
    this.letter = letter;
  }

  void appendIndex(int index) {
    indices.offer(index);
    lastIndex = index;
  }
}
