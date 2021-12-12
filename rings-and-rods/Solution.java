import java.util.stream.IntStream;

class Solution {
  public int countPoints(String rings) {
    return (int)
        IntStream.range(0, 10)
            .filter(
                i ->
                    IntStream.range(0, rings.length() / 2)
                            .filter(j -> rings.charAt(j * 2 + 1) - '0' == i)
                            .map(j -> rings.charAt(j * 2))
                            .distinct()
                            .count()
                        == 3)
            .count();
  }
}