import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<String> fullJustify(String[] words, int maxWidth) {
    List<String> lines = new ArrayList<>();
    List<String> lineWords = new ArrayList<>();
    List<Integer> spaces = new ArrayList<>();
    int length = 0;
    int index = 0;
    while (index != words.length) {
      int nextLength = length + ((length == 0) ? 0 : 1) + words[index].length();
      if (nextLength <= maxWidth) {
        if (length != 0) {
          spaces.add(1);
        }
        lineWords.add(words[index]);
        length = nextLength;

        ++index;
      } else {
        if (lineWords.size() > 1) {
          packExtraSpaces(spaces, maxWidth - length);
        } else {
          spaces.add(maxWidth - length);
        }
        lines.add(combineLine(lineWords, spaces));

        lineWords.clear();
        spaces.clear();
        length = 0;
      }
    }

    spaces.add(maxWidth - length);
    lines.add(combineLine(lineWords, spaces));

    return lines;
  }

  void packExtraSpaces(List<Integer> spaces, int spacesLeft) {
    int extra = spacesLeft / spaces.size();
    spacesLeft -= extra * spaces.size();
    for (int i = 0; i < spaces.size(); ++i) {
      spaces.set(i, spaces.get(i) + extra + ((i < spacesLeft) ? 1 : 0));
    }
  }

  String combineLine(List<String> lineWords, List<Integer> spaces) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < Math.max(lineWords.size(), spaces.size()); ++i) {
      if (i < lineWords.size()) {
        result.append(lineWords.get(i));
      }
      if (i < spaces.size()) {
        result.append(" ".repeat(spaces.get(i)));
      }
    }

    return result.toString();
  }
}
