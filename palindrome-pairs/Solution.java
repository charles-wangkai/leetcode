import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> palindromePairs(String[] words) {
    Map<String, Integer> wordToIndex =
        IntStream.range(0, words.length).boxed().collect(Collectors.toMap(i -> words[i], i -> i));
    Set<Integer> wordLengths = Arrays.stream(words).map(String::length).collect(Collectors.toSet());

    List<List<Integer>> pairs = new ArrayList<>();
    for (int i = 0; i < words.length; ++i) {
      searchForRight(pairs, wordToIndex, wordLengths, words[i], i);
      searchForLeft(pairs, wordToIndex, wordLengths, words[i], i);
    }

    return pairs;
  }

  void searchForRight(
      List<List<Integer>> pairs,
      Map<String, Integer> wordToIndex,
      Set<Integer> wordLengths,
      String word,
      int index) {
    for (int middleLength = 0; middleLength <= word.length(); ++middleLength) {
      if (wordLengths.contains(word.length() - middleLength)) {
        String middle = word.substring(word.length() - middleLength);
        String left = word.substring(0, word.length() - middleLength);
        String right = reverse(left);
        if (wordToIndex.containsKey(right)
            && wordToIndex.get(right) != index
            && isPalindrome(middle)) {
          pairs.add(List.of(index, wordToIndex.get(right)));
        }
      }
    }
  }

  void searchForLeft(
      List<List<Integer>> pairs,
      Map<String, Integer> wordToIndex,
      Set<Integer> wordLengths,
      String word,
      int index) {
    for (int middleLength = 1; middleLength <= word.length(); ++middleLength) {
      if (wordLengths.contains(word.length() - middleLength)) {
        String middle = word.substring(0, middleLength);
        String right = word.substring(middleLength);
        String left = reverse(right);
        if (wordToIndex.containsKey(left)
            && wordToIndex.get(left) != index
            && isPalindrome(middle)) {
          pairs.add(List.of(wordToIndex.get(left), index));
        }
      }
    }
  }

  String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  boolean isPalindrome(String s) {
    for (int i = 0, j = s.length() - 1; i < j; ++i, --j) {
      if (s.charAt(i) != s.charAt(j)) {
        return false;
      }
    }

    return true;
  }
}
