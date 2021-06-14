import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {
  public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> pairs = new ArrayList<>();

    Map<String, Integer> wordToIndex =
        IntStream.range(0, words.length).boxed().collect(Collectors.toMap(i -> words[i], i -> i));

    for (int i = 0; i < words.length; ++i) {
      searchForRight(pairs, words, wordToIndex, i);
      searchForLeft(pairs, words, wordToIndex, i);
    }

    return pairs;
  }

  void searchForRight(
      List<List<Integer>> pairs, String[] words, Map<String, Integer> wordToIndex, int index) {
    for (int middleLength = 0; middleLength <= words[index].length(); ++middleLength) {
      String left = words[index].substring(0, words[index].length() - middleLength);
      String middle = words[index].substring(words[index].length() - middleLength);

      if (!isPalindrome(middle)) {
        continue;
      }

      String right = reverse(left);
      if (!wordToIndex.containsKey(right) || wordToIndex.get(right) == index) {
        continue;
      }

      pairs.add(List.of(index, wordToIndex.get(right)));
    }
  }

  void searchForLeft(
      List<List<Integer>> pairs, String[] words, Map<String, Integer> wordToIndex, int index) {
    for (int middleLength = 1; middleLength <= words[index].length(); ++middleLength) {
      String right = words[index].substring(middleLength);
      String middle = words[index].substring(0, middleLength);

      if (!isPalindrome(middle)) {
        continue;
      }

      String left = reverse(right);
      if (!wordToIndex.containsKey(left) || wordToIndex.get(left) == index) {
        continue;
      }

      pairs.add(List.of(wordToIndex.get(left), index));
    }
  }

  String reverse(String s) {
    return new StringBuilder(s).reverse().toString();
  }

  boolean isPalindrome(String s) {
    return s.equals(reverse(s));
  }
}
