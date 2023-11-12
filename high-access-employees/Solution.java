import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

class Solution {
  public List<String> findHighAccessEmployees(List<List<String>> access_times) {
    Map<String, List<Integer>> nameToTimes = new HashMap<>();
    for (List<String> access_time : access_times) {
      nameToTimes.putIfAbsent(access_time.get(0), new ArrayList<>());
      nameToTimes.get(access_time.get(0)).add(toTime(access_time.get(1)));
    }
    for (List<Integer> times : nameToTimes.values()) {
      Collections.sort(times);
    }

    return nameToTimes.keySet().stream()
        .filter(
            name -> {
              List<Integer> times = nameToTimes.get(name);

              return IntStream.range(0, times.size() - 2)
                  .anyMatch(i -> times.get(i + 2) - times.get(i) < 60);
            })
        .toList();
  }

  int toTime(String s) {
    return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(2));
  }
}
