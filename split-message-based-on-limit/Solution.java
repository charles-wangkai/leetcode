import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Solution {
  static final int B_LENGTH_LIMIT = 4;

  public String[] splitMessage(String message, int limit) {
    for (int bLength = 1; bLength <= B_LENGTH_LIMIT; ++bLength) {
      List<String> parts = computeParts(message, limit, bLength);
      if (parts != null) {
        return parts.toArray(String[]::new);
      }
    }

    return new String[0];
  }

  List<String> computeParts(String message, int limit, int bLength) {
    List<String> segments = new ArrayList<>();
    int index = 0;
    for (int a = 1; index < message.length(); ++a) {
      int size = limit - (3 + String.valueOf(a).length() + bLength);
      if (size <= 0) {
        return null;
      }

      segments.add(message.substring(index, Math.min(message.length(), index + size)));
      index += size;
    }

    return ((String.valueOf(segments.size()).length() == bLength))
        ? IntStream.range(0, segments.size())
            .mapToObj(i -> String.format("%s<%d/%d>", segments.get(i), i + 1, segments.size()))
            .toList()
        : null;
  }
}
