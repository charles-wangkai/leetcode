import java.util.List;

class Solution {
  public String applySubstitutions(List<List<String>> replacements, String text) {
    String result = text;
    while (true) {
      String next = result;
      for (List<String> replacement : replacements) {
        next = next.replace("%" + replacement.get(0) + "%", replacement.get(1));
      }

      if (next.equals(result)) {
        break;
      }

      result = next;
    }

    return result;
  }
}