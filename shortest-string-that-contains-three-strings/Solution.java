import java.util.Comparator;
import java.util.stream.Stream;

class Solution {
  public String minimumString(String a, String b, String c) {
    return Stream.of(
            combine(combine(a, b), c),
            combine(combine(a, c), b),
            combine(combine(b, c), a),
            combine(combine(b, a), c),
            combine(combine(c, a), b),
            combine(combine(c, b), a))
        .min(Comparator.comparing(String::length).thenComparing(Comparator.naturalOrder()))
        .get();
  }

  String combine(String s1, String s2) {
    if (s1.contains(s2)) {
      return s1;
    }

    for (int i = 0; ; ++i) {
      if (s2.startsWith(s1.substring(i))) {
        return s1.substring(0, i) + s2;
      }
    }
  }
}
