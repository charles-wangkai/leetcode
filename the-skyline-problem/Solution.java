import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
            .sorted((c1, c2) -> Integer.compare(c1.x, c2.x))
            .toArray(Corner[]::new);

    SortedMap<Integer, Integer> heightToCount = new TreeMap<>();
    heightToCount.put(0, 1);
    List<List<Integer>> skyline = new ArrayList<>();
    for (Corner corner : corners) {
      if (corner.leftOrRight) {
        heightToCount.put(corner.y, heightToCount.getOrDefault(corner.y, 0) + 1);
      } else {
        heightToCount.put(corner.y, heightToCount.get(corner.y) - 1);
        heightToCount.remove(corner.y, 0);
      }

      if (!skyline.isEmpty() && corner.x == skyline.get(skyline.size() - 1).get(0)) {
        skyline.remove(skyline.size() - 1);
      }

      int nextHeight = heightToCount.lastKey();
      if (skyline.isEmpty() || nextHeight != skyline.get(skyline.size() - 1).get(1)) {
        skyline.add(Arrays.asList(corner.x, nextHeight));
      }
    }

    return skyline;
  }
}

class Corner {
  int x;
  int y;
  boolean leftOrRight;

  Corner(int x, int y, boolean leftOrRight) {
    this.x = x;
    this.y = y;
    this.leftOrRight = leftOrRight;
  }
}
