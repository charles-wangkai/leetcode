import java.util.stream.IntStream;

class Solution {
  public int minDeletionSize(String[] strs) {
    return (int)
        IntStream.range(0, strs[0].length())
            .filter(
                c ->
                    IntStream.range(0, strs.length - 1)
                        .anyMatch(i -> strs[i].charAt(c) > strs[i + 1].charAt(c)))
            .count();
  }
}
