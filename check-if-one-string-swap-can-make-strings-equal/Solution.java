import java.util.stream.IntStream;

class Solution {
  public boolean areAlmostEqual(String s1, String s2) {
    int[] diffIndices =
        IntStream.range(0, s1.length()).filter(i -> s1.charAt(i) != s2.charAt(i)).toArray();

    return diffIndices.length == 0
        || (diffIndices.length == 2
            && s1.charAt(diffIndices[0]) == s2.charAt(diffIndices[1])
            && s1.charAt(diffIndices[1]) == s2.charAt(diffIndices[0]));
  }
}
