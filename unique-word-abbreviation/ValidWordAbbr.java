import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ValidWordAbbr {
  Map<String, Set<String>> abbreviation2words = new HashMap<>();

  public ValidWordAbbr(String[] dictionary) {
    for (String word : dictionary) {
      String abbreviation = generateAbbreviation(word);
      if (!abbreviation2words.containsKey(abbreviation)) {
        abbreviation2words.put(abbreviation, new HashSet<>());
      }
      abbreviation2words.get(abbreviation).add(word);
    }
  }

  public boolean isUnique(String word) {
    String abbreviation = generateAbbreviation(word);
    if (!abbreviation2words.containsKey(abbreviation)) {
      return true;
    }
    for (String w : abbreviation2words.get(abbreviation)) {
      if (!w.equals(word)) {
        return false;
      }
    }
    return true;
  }

  String generateAbbreviation(String word) {
    if (word.length() < 3) {
      return word;
    }
    return String.format(
        "%c%d%c", word.charAt(0), word.length() - 2, word.charAt(word.length() - 1));
  }
}

// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");
