import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

class Solution {
  public List<List<Integer>> getSkyline(int[][] buildings) {
    Corner[] corners =
        Arrays.stream(buildings)
            .flatMap(
                building ->
                    Stream.of(
                        new Corner(building[0], building[2], true),
                        new Corner(building[1], building[2], false)))
            .sorted(Comparator.comparing(Corner::x))
            .toArray(Corner[]::new);

    SortedMap<Integer, Integer> heightToCount = new TreeMap<>();
    updateMap(heightToCount, 0, 1);
    List<List<Integer>> skyline = new ArrayList<>();
    for (Corner corner : corners) {
      updateMap(heightToCount, corner.y(), corner.leftOrRight() ? 1 : -1);

      if (!skyline.isEmpty() && corner.x() == skyline.get(skyline.size() - 1).get(0)) {
        skyline.remove(skyline.size() - 1);
      }

      int nextHeight = heightToCount.lastKey();
      if (skyline.isEmpty() || nextHeight != skyline.get(skyline.size() - 1).get(1)) {
        skyline.add(List.of(corner.x(), nextHeight));
      }
    }

    return skyline;
  }

  void updateMap(Map<Integer, Integer> map, int key, int delta) {
    map.put(key, map.getOrDefault(key, 0) + delta);
    map.remove(key, 0);
  }
}

record Corner(int x, int y, boolean leftOrRight) {}
