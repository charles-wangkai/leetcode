import java.util.ArrayList;
import java.util.List;

class Solution {
  public List<Integer> lastVisitedIntegers(List<String> words) {
    List<Integer> result = new ArrayList<>();
    List<Integer> values = new ArrayList<>();
    int prevCount = 0;
    for (String word : words) {
      if (word.equals("prev")) {
        ++prevCount;
        result.add((prevCount <= values.size()) ? values.get(prevCount - 1) : -1);
      } else {
        prevCount = 0;
        values.add(0, Integer.parseInt(word));
      }
    }

    return result;
  }
}
