import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class Solution {
  public int maxPointsInsideSquare(int[][] points, String s) {
    SortedMap<Integer, List<Character>> distanceToTags = new TreeMap<>();
    for (int i = 0; i < points.length; ++i) {
      int distance = Math.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
      distanceToTags.putIfAbsent(distance, new ArrayList<>());
      distanceToTags.get(distance).add(s.charAt(i));
    }

    int result = 0;
    Set<Character> seen = new HashSet<>();
    for (List<Character> tags : distanceToTags.values()) {
      for (char tag : tags) {
        if (seen.contains(tag)) {
          return result;
        }

        seen.add(tag);
      }

      result += tags.size();
    }

    return result;
  }
}