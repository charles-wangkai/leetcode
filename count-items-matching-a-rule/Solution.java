import static java.util.Map.entry;

import java.util.List;
import java.util.Map;

class Solution {
  static final Map<String, Integer> KEY_TO_INDEX =
      Map.ofEntries(entry("type", 0), entry("color", 1), entry("name", 2));

  public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
    return (int)
        items.stream()
            .filter(item -> item.get(KEY_TO_INDEX.get(ruleKey)).equals(ruleValue))
            .count();
  }
}
