import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordDistance {
  private Map<String, List<Integer>> word2indices = new HashMap<>();
  private Map<Set<String>, Integer> history = new HashMap<>();

  public WordDistance(String[] words) {
    for (int i = 0; i < words.length; i++) {
      if (!word2indices.containsKey(words[i])) {
        word2indices.put(words[i], new ArrayList<>());
      }
      word2indices.get(words[i]).add(i);
    }
  }

  public int shortest(String word1, String word2) {
    Set<String> pair = new HashSet<>();
    pair.add(word1);
    pair.add(word2);

    if (history.containsKey(pair)) {
      return history.get(pair);
    }

    int minDistance = Integer.MAX_VALUE;
    List<Integer> indices1 = word2indices.get(word1);
    List<Integer> indices2 = word2indices.get(word2);
    int i1 = 0;
    int i2 = 0;
    while (i1 != indices1.size() || i2 != indices2.size()) {
      if (i2 == indices2.size() || (i1 != indices1.size() && indices1.get(i1) < indices2.get(i2))) {
        if (i2 > 0) {
          minDistance = Math.min(minDistance, indices1.get(i1) - indices2.get(i2 - 1));
        }
        i1++;
      } else {
        if (i1 > 0) {
          minDistance = Math.min(minDistance, indices2.get(i2) - indices1.get(i1 - 1));
        }
        i2++;
      }
    }

    history.put(pair, minDistance);
    return minDistance;
  }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
