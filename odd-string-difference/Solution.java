import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
  public String oddString(String[] words) {
    for (int i = 0; ; ++i) {
      Map<Integer, Integer> diffToCount = new HashMap<>();
      for (String word : words) {
        int diff = word.charAt(i + 1) - word.charAt(i);
        diffToCount.put(diff, diffToCount.getOrDefault(diff, 0) + 1);
      }

      if (diffToCount.size() != 1) {
        int i_ = i;
        return Arrays.stream(words)
            .filter(word -> diffToCount.get(word.charAt(i_ + 1) - word.charAt(i_)) == 1)
            .findAny()
            .get();
      }
    }
  }
}
