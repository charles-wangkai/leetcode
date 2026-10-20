import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public List<Integer> goodIndices(String s) {
    return IntStream.range(0, s.length())
        .filter(
            i -> {
              String str = String.valueOf(i);

              return str.length() <= i + 1 && s.startsWith(str, i - str.length() + 1);
            })
        .boxed()
        .toList();
  }
}