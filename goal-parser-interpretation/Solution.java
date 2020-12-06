import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Solution {
  static Map<String, String> TRANSLATION = new HashMap<>();

  static {
    TRANSLATION.put("G", "G");
    TRANSLATION.put("()", "o");
    TRANSLATION.put("(al)", "al");
  }

  public String interpret(String command) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index != command.length()) {
      for (Entry<String, String> entry : TRANSLATION.entrySet()) {
        if (command.startsWith(entry.getKey(), index)) {
          result.append(entry.getValue());
          index += entry.getKey().length();

          break;
        }
      }
    }

    return result.toString();
  }
}
