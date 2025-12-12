import static java.util.Map.entry;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  static final Map<String, Integer> EVENT_TYPE_TO_ORDER =
      Map.ofEntries(entry("OFFLINE", 0), entry("MESSAGE", 1));

  public int[] countMentions(int numberOfUsers, List<List<String>> events) {
    Collections.sort(
        events,
        Comparator.<List<String>, Integer>comparing(event -> Integer.parseInt(event.get(1)))
            .thenComparing(event -> EVENT_TYPE_TO_ORDER.get(event.get(0))));

    int[] result = new int[numberOfUsers];

    int[] availableTimes = new int[numberOfUsers];
    for (List<String> event : events) {
      int timestamp = Integer.parseInt(event.get(1));

      if (event.get(0).equals("MESSAGE")) {
        int[] ids;
        String mentions = event.get(2);
        if (mentions.equals("ALL")) {
          ids = IntStream.range(0, result.length).toArray();
        } else if (mentions.equals("HERE")) {
          ids =
              IntStream.range(0, result.length)
                  .filter(i -> availableTimes[i] <= timestamp)
                  .toArray();
        } else {
          ids =
              Arrays.stream(mentions.split(" "))
                  .map(s -> s.substring(2))
                  .mapToInt(Integer::parseInt)
                  .toArray();
        }

        for (int id : ids) {
          ++result[id];
        }
      } else {
        int id = Integer.parseInt(event.get(2));
        availableTimes[id] = timestamp + 60;
      }
    }

    return result;
  }
}