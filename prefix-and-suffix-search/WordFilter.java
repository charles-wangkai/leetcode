import java.util.HashMap;
import java.util.Map;

class WordFilter {
  Map<String, Integer> keyToIndex = new HashMap<>();

  public WordFilter(String[] words) {
    for (int i = 0; i < words.length; ++i) {
      for (int prefixLength = 0; prefixLength <= words[i].length(); ++prefixLength) {
        for (int suffixLength = 0; suffixLength <= words[i].length(); ++suffixLength) {
          keyToIndex.put(
              generateKey(
                  words[i].substring(0, prefixLength),
                  words[i].substring(words[i].length() - suffixLength)),
              i);
        }
      }
    }
  }

  public int f(String prefix, String suffix) {
    return keyToIndex.getOrDefault(generateKey(prefix, suffix), -1);
  }

  String generateKey(String prefix, String suffix) {
    return String.format("%s|%s", prefix, suffix);
  }
}

// Your WordFilter object will be instantiated and called as such:
// WordFilter obj = new WordFilter(words);
// int param_1 = obj.f(prefix,suffix);
