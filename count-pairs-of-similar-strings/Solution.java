import java.util.stream.Collectors;

class Solution {
  public int similarPairs(String[] words) {
    int result = 0;
    for (int i = 0; i < words.length; ++i) {
      for (int j = i + 1; j < words.length; ++j) {
        if (words[i]
            .chars()
            .boxed()
            .collect(Collectors.toSet())
            .equals(words[j].chars().boxed().collect(Collectors.toSet()))) {
          ++result;
        }
      }
    }

    return result;
  }
}
