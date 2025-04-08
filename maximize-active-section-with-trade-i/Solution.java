import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  public int maxActiveSectionsAfterTrade(String s) {
    List<Segment> segments = new ArrayList<>();
    char letter = 0;
    int length = 0;
    for (int i = 0; i <= s.length(); ++i) {
      if (i != s.length() && s.charAt(i) == letter) {
        ++length;
      } else {
        if (length != 0) {
          segments.add(new Segment(letter, length));
        }
        if (i != s.length()) {
          letter = s.charAt(i);
          length = 1;
        }
      }
    }

    return (int) s.chars().filter(c -> c == '1').count()
        + IntStream.range(1, segments.size() - 1)
            .filter(i -> segments.get(i).letter() == '1')
            .map(i -> segments.get(i - 1).length() + segments.get(i + 1).length())
            .max()
            .orElse(0);
  }
}

record Segment(char letter, int length) {}
