import java.util.LinkedList;
import java.util.Queue;

public class StringIterator {
  Queue<LetterAndCount> letterAndCountList;

  public StringIterator(String compressedString) {
    letterAndCountList = new LinkedList<>();

    int index = 0;
    while (index < compressedString.length()) {
      char letter = compressedString.charAt(index);
      index++;

      int count = 0;
      while (index < compressedString.length()
          && Character.isDigit(compressedString.charAt(index))) {
        count = count * 10 + (compressedString.charAt(index) - '0');
        index++;
      }

      letterAndCountList.add(new LetterAndCount(letter, count));
    }
  }

  public char next() {
    if (hasNext()) {
      char result = letterAndCountList.peek().letter;

      letterAndCountList.peek().count--;
      if (letterAndCountList.peek().count == 0) {
        letterAndCountList.poll();
      }

      return result;
    } else {
      return ' ';
    }
  }

  public boolean hasNext() {
    return !letterAndCountList.isEmpty();
  }
}

class LetterAndCount {
  char letter;
  int count;

  LetterAndCount(char letter, int count) {
    this.letter = letter;
    this.count = count;
  }
}

// Your StringIterator object will be instantiated and called as such:
// StringIterator obj = new StringIterator(compressedString);
// char param_1 = obj.next();
// boolean param_2 = obj.hasNext();
