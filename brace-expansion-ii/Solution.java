import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
  public List<String> braceExpansionII(String expression) {
    return expand(expression).stream().sorted().toList();
  }

  Set<String> expand(String expression) {
    if (expression.length() == 1) {
      return Set.of(expression);
    }
    if (isUnion(expression)) {
      return union(expression);
    }

    return concat(expression);
  }

  Set<String> concat(String expression) {
    List<Set<String>> partsList = new ArrayList<>();

    int beginIndex = -1;
    int depth = 0;
    for (int i = 0; i < expression.length(); ++i) {
      char c = expression.charAt(i);

      if (c == '{') {
        if (depth == 0) {
          beginIndex = i;
        }

        ++depth;
      } else if (c == '}') {
        --depth;

        if (depth == 0) {
          partsList.add(expand(expression.substring(beginIndex, i + 1)));
        }
      } else if (depth == 0) {
        partsList.add(expand(expression.substring(i, i + 1)));
      }
    }

    Set<String> concatenated = new HashSet<>();
    search(concatenated, partsList, 0, new StringBuilder());

    return concatenated;
  }

  void search(
      Set<String> concatenated, List<Set<String>> partsList, int index, StringBuilder word) {
    if (index == partsList.size()) {
      concatenated.add(word.toString());

      return;
    }

    for (String part : partsList.get(index)) {
      word.append(part);

      search(concatenated, partsList, index + 1, word);

      word.delete(word.length() - part.length(), word.length());
    }
  }

  Set<String> union(String expression) {
    Set<String> result = new HashSet<>();

    int beginIndex = 1;
    int depth = 0;
    for (int i = 1; i <= expression.length() - 1; ++i) {
      if (i == expression.length() - 1 || (depth == 0 && expression.charAt(i) == ',')) {
        result.addAll(expand(expression.substring(beginIndex, i)));

        beginIndex = i + 1;
      } else if (expression.charAt(i) == '{') {
        ++depth;
      } else if (expression.charAt(i) == '}') {
        --depth;
      }
    }

    return result;
  }

  boolean isUnion(String expression) {
    if (expression.charAt(0) != '{') {
      return false;
    }

    int depth = 0;
    for (int i = 0; ; ++i) {
      char c = expression.charAt(i);

      if (c == '{') {
        ++depth;
      } else if (c == '}') {
        --depth;

        if (depth == 0) {
          return i == expression.length() - 1;
        }
      }
    }
  }
}
