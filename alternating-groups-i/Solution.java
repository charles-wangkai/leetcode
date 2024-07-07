import java.util.stream.IntStream;

class Solution {
  public int numberOfAlternatingGroups(int[] colors) {
    return (int)
        IntStream.range(0, colors.length)
            .filter(
                i ->
                    colors[i] != colors[(i + 1) % colors.length]
                        && colors[(i + 1) % colors.length] != colors[(i + 2) % colors.length])
            .count();
  }
}