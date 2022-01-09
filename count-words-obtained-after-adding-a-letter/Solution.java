import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {
  public int wordCount(String[] startWords, String[] targetWords) {
    Set<Integer> startMasks =
        Arrays.stream(startWords)
            .mapToInt(startWord -> startWord.chars().map(ch -> 1 << (ch - 'a')).sum())
            .boxed()
            .collect(Collectors.toSet());

    return (int)
        Arrays.stream(targetWords)
            .filter(
                targetWord -> {
                  int mask = targetWord.chars().map(ch -> 1 << (ch - 'a')).sum();
                  int rest = mask;
                  while (rest != 0) {
                    int bitValue = Integer.highestOneBit(rest);
                    if (startMasks.contains(mask - bitValue)) {
                      return true;
                    }

                    rest -= bitValue;
                  }

                  return false;
                })
            .count();
  }
}