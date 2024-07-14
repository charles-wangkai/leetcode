import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
  public String countOfAtoms(String formula) {
    Map<String, Integer> elementToCount = parse(formula);

    return elementToCount.keySet().stream()
        .sorted()
        .map(
            element ->
                element + ((elementToCount.get(element) == 1) ? "" : elementToCount.get(element)))
        .collect(Collectors.joining());
  }

  Map<String, Integer> parse(String s) {
    Map<String, Integer> elementToCount = new HashMap<>();
    int index = 0;
    while (index != s.length()) {
      if (s.charAt(index) == '(') {
        int partLength = readPart(s, index);

        int countLength = readNumber(s, index + partLength);
        int count =
            (countLength == 0)
                ? 1
                : Integer.parseInt(
                    s.substring(index + partLength, index + partLength + countLength));

        elementToCount =
            add(
                elementToCount,
                multiply(parse(s.substring(index + 1, index + partLength - 1)), count));

        index += partLength + countLength;
      } else {
        int elementLength = readElement(s, index);
        String element = s.substring(index, index + elementLength);
        index += elementLength;

        int countLength = readNumber(s, index);
        int count =
            (countLength == 0) ? 1 : Integer.parseInt(s.substring(index, index + countLength));
        index += countLength;

        elementToCount.put(element, elementToCount.getOrDefault(element, 0) + count);
      }
    }

    return elementToCount;
  }

  int readPart(String s, int index) {
    ++index;
    int depth = 1;
    int length = 1;
    while (depth != 0) {
      char c = s.charAt(index);
      if (c == '(') {
        ++depth;
      } else if (c == ')') {
        --depth;
      }

      ++index;
      ++length;
    }

    return length;
  }

  int readNumber(String s, int index) {
    int length = 0;
    while (index != s.length() && Character.isDigit(s.charAt(index))) {
      ++index;
      ++length;
    }

    return length;
  }

  int readElement(String s, int index) {
    ++index;
    int length = 1;
    while (index != s.length() && Character.isLowerCase(s.charAt(index))) {
      ++index;
      ++length;
    }

    return length;
  }

  Map<String, Integer> multiply(Map<String, Integer> elementToCount, int factor) {
    return elementToCount.entrySet().stream()
        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() * factor));
  }

  Map<String, Integer> add(
      Map<String, Integer> elementToCount1, Map<String, Integer> elementToCount2) {
    return Stream.concat(elementToCount1.entrySet().stream(), elementToCount2.entrySet().stream())
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (count1, count2) -> count1 + count2));
  }
}
