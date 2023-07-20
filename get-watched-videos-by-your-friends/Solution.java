import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.stream.IntStream;

public class Solution {
  public List<String> watchedVideosByFriends(
      List<List<String>> watchedVideos, int[][] friends, int id, int level) {
    int n = friends.length;
    int[] distances = IntStream.range(0, n).map(i -> -1).toArray();
    distances[id] = 0;
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(id);
    Map<String, Integer> videoToCount = new HashMap<>();
    while (!queue.isEmpty()) {
      int head = queue.poll();
      if (distances[head] == level) {
        for (String video : watchedVideos.get(head)) {
          videoToCount.put(video, videoToCount.getOrDefault(video, 0) + 1);
        }
      } else {
        for (int friend : friends[head]) {
          if (distances[friend] == -1) {
            distances[friend] = distances[head] + 1;
            queue.offer(friend);
          }
        }
      }
    }

    return videoToCount.entrySet().stream()
        .sorted(
            (e1, e2) ->
                (e1.getValue().intValue() != e2.getValue())
                    ? Integer.compare(e1.getValue(), e2.getValue())
                    : e1.getKey().compareTo(e2.getKey()))
        .map(Entry::getKey)
        .toList();
  }
}
