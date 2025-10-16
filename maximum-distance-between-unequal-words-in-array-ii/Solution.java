import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
  public int maxDistance(String[] words) {
    Map<String, List<Range>> wordToRanges = new HashMap<>();
    for (int i = 0; i < words.length; ++i) {
      wordToRanges.putIfAbsent(words[i], new ArrayList<>());

      if (!wordToRanges.get(words[i]).isEmpty()
          && wordToRanges.get(words[i]).getLast().endIndex + 1 == i) {
        ++wordToRanges.get(words[i]).getLast().endIndex;
      } else {
        wordToRanges.get(words[i]).add(new Range(i, i));
      }
    }

    return wordToRanges.values().stream()
        .mapToInt(
            ranges ->
                ((ranges.getLast().endIndex == words.length - 1)
                        ? (ranges.getLast().beginIndex - 1)
                        : (words.length - 1))
                    - ranges.getFirst().beginIndex
                    + 1)
        .max()
        .getAsInt();
  }
}

class Range {
  int beginIndex;
  int endIndex;

  Range(int beginIndex, int endIndex) {
    this.beginIndex = beginIndex;
    this.endIndex = endIndex;
  }
}