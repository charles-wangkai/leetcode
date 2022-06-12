import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  static final int ALPHABET_SIZE = 26;

  public long distinctNames(String[] ideas) {
    Set<String> ideaSet = Arrays.stream(ideas).collect(Collectors.toSet());

    @SuppressWarnings("unchecked")
    List<Integer>[] swappedLists = new List[ideas.length];
    for (int i = 0; i < swappedLists.length; ++i) {
      swappedLists[i] = new ArrayList<>();
    }

    int[][] ideaNums = new int[ALPHABET_SIZE][ALPHABET_SIZE];
    for (int i = 0; i < ideas.length; ++i) {
      for (int swapped = 0; swapped < ALPHABET_SIZE; ++swapped) {
        if (!ideaSet.contains(String.valueOf((char) (swapped + 'a')) + ideas[i].substring(1))) {
          swappedLists[i].add(swapped);
          ++ideaNums[ideas[i].charAt(0) - 'a'][swapped];
        }
      }
    }

    long result = 0;
    for (int i = 0; i < ideas.length; ++i) {
      for (int swapped : swappedLists[i]) {
        result += ideaNums[swapped][ideas[i].charAt(0) - 'a'];
      }
    }

    return result;
  }
}