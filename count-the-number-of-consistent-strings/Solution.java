import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int countConsistentStrings(String allowed, String[] words) {
    Set<Integer> allowedSet = allowed.chars().boxed().collect(Collectors.toSet());

    return (int)
        Arrays.stream(words).filter(word -> word.chars().allMatch(allowedSet::contains)).count();
  }
}
