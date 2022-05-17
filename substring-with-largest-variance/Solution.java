import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int largestVariance(String s) {
    int result = 0;
    for (char letter1 = 'a'; letter1 <= 'z'; ++letter1) {
      for (char letter2 = (char) (letter1 + 1); letter2 <= 'z'; ++letter2) {
        result = Math.max(result, computeMaxVariance(s, letter1, letter2));
      }
    }

    return result;
  }

  int computeMaxVariance(String s, char letter1, char letter2) {
    List<Element> elements = new ArrayList<>();
    for (char letter : s.toCharArray()) {
      if (letter == letter1 || letter == letter2) {
        if (!elements.isEmpty() && letter == elements.get(elements.size() - 1).letter) {
          ++elements.get(elements.size() - 1).count;
        } else {
          elements.add(new Element(letter, 1));
        }
      }
    }

    SortedMap<Integer, Integer> diffToLastIndex = new TreeMap<>();
    int diff = 0;
    for (int i = 0; i < elements.size(); ++i) {
      diff += ((elements.get(i).letter == letter1) ? 1 : -1) * elements.get(i).count;
      diffToLastIndex.put(diff, i);
    }

    int result =
        (elements.size() >= 2)
            ? elements.stream().mapToInt(element -> element.count - 1).max().getAsInt()
            : 0;
    diff = 0;
    for (int i = 0; i < elements.size(); ++i) {
      int maxDiff = Integer.MAX_VALUE;
      while (!diffToLastIndex.isEmpty()) {
        maxDiff = diffToLastIndex.lastKey();
        if (diffToLastIndex.get(maxDiff) > i) {
          break;
        }

        diffToLastIndex.remove(maxDiff);
      }
      if (diffToLastIndex.isEmpty()) {
        break;
      }

      int minDiff;
      while (true) {
        minDiff = diffToLastIndex.firstKey();
        if (diffToLastIndex.get(minDiff) > i) {
          break;
        }

        diffToLastIndex.remove(minDiff);
      }

      result = Math.max(result, Math.max(Math.abs(diff - maxDiff), Math.abs(diff - minDiff)));

      diff += ((elements.get(i).letter == letter1) ? 1 : -1) * elements.get(i).count;
    }

    return result;
  }
}

class Element {
  char letter;
  int count;

  Element(char letter, int count) {
    this.letter = letter;
    this.count = count;
  }
}