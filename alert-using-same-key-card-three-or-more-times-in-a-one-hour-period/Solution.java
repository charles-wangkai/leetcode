import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.IntStream;

class Solution {
  public List<String> alertNames(String[] keyName, String[] keyTime) {
    int[] minutes =
        Arrays.stream(keyTime)
            .mapToInt(
                t -> Integer.parseInt(t.substring(0, 2)) * 60 + Integer.parseInt(t.substring(3)))
            .toArray();
    int[] sortedIndices =
        IntStream.range(0, minutes.length)
            .boxed()
            .sorted((i1, i2) -> Integer.compare(minutes[i1], minutes[i2]))
            .mapToInt(x -> x)
            .toArray();

    Set<String> result = new HashSet<>();
    Map<String, Queue<Integer>> nameToTimes = new HashMap<>();
    for (int sortedIndex : sortedIndices) {
      String name = keyName[sortedIndex];
      if (!nameToTimes.containsKey(name)) {
        nameToTimes.put(name, new LinkedList<>());
      }

      Queue<Integer> times = nameToTimes.get(name);
      times.offer(minutes[sortedIndex]);
      while (minutes[sortedIndex] - times.peek() > 60) {
        times.poll();
      }
      if (times.size() >= 3) {
        result.add(name);
      }
    }

    return result.stream().sorted().toList();
  }
}
