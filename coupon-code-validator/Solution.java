import static java.util.Map.entry;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final Map<String, Integer> BUSINESS_LINE_TO_ORDER =
      Map.ofEntries(
          entry("electronics", 0),
          entry("grocery", 1),
          entry("pharmacy", 2),
          entry("restaurant", 3));

  public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
    return IntStream.range(0, code.length)
        .filter(
            i ->
                !code[i].isEmpty()
                    && code[i]
                        .chars()
                        .allMatch(
                            c ->
                                (c >= 'a' && c <= 'z')
                                    || (c >= 'A' && c <= 'Z')
                                    || (c >= '0' && c <= '9')
                                    || c == '_')
                    && BUSINESS_LINE_TO_ORDER.containsKey(businessLine[i])
                    && isActive[i])
        .boxed()
        .sorted(
            Comparator.<Integer, Integer>comparing(i -> BUSINESS_LINE_TO_ORDER.get(businessLine[i]))
                .thenComparing(i -> code[i]))
        .map(i -> code[i])
        .toList();
  }
}